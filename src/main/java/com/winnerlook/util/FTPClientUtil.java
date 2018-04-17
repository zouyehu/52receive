package com.winnerlook.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * FTP工具类
 * @Copyright 
 * @author beck
 * @date 
 * =================Modify Record=================
 * @Modifier			@date			@Content
 * beck				2016-09-22		新增
 */
public class FTPClientUtil
{
	private static Logger log = Logger.getLogger(FTPClientUtil.class);
	private static FTPClient ftpClient =  null;
	
	private static String host = "";
	private static int port = 0;
	private static String username = "";
	private static String password = "";

	/**
	 * 上传文件到FTP根目录
	 * @author beck 
	 * @date 2016-09-22
	 * @param 	fileName		文件名
	 * @param 	inputStream		输入流
	 * @return	boolean			是否上传成功
	 */
	public static boolean uploadFile(String fileName, InputStream inputStream)
	{
		return uploadFile(fileName, inputStream);
	}
	
	/**
	 * 上传文件到指定目录
	 * @author beck 
	 * @date 2016-09-22
	 * @param 	fileName		文件名
	 * @param 	inputStream		输入流
	 * @param	dirAry			目录数组
	 * @return	boolean			是否上传成功
	 * 注：dirAry为层级目录，非同一级别多个目录，例如：数组中有三个元素【aa】、【bb】、【cc】，
	 * 则文件上传到FTP服务器上的aa下的bb下的cc目录下
	 */
	public static boolean uploadFile(String fileName, InputStream inputStream, String... dirAry)
	{
		try
		{
			//1、初始化FTPClient
			ftpClient = initFtpClient();
			if(ftpClient == null)
				return false;
			
			//2、创建目录
			if(dirAry != null && dirAry.length > 0)
			{
				if(!mkDirs(dirAry)) return false;
			}
			
			//3、上传文件
			return ftpClient.storeFile(fileName, inputStream);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				inputStream.close();
				if(ftpClient != null)
					ftpClient.disconnect();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 到指定目录删除文件
	 * @author beck 
	 * @date 2016-09-22
	 * @param 	fileName		文件名
	 * @param	dirAry			目录数组
	 * @return	boolean			是否删除成功
	 * 注：dirAry为层级目录，非同一级别多个目录，例如：数组中有三个元素【aa】、【bb】、【cc】，
	 * 则到FTP服务器上的aa下的bb下的cc目录下的删除文件
	 */
	public static boolean deleteFile(String fileName, String... dirAry)
	{
		try
		{
			//1、初始化FTPClient
			ftpClient = initFtpClient();
			if(ftpClient == null)
				return false;

			//2、切换FTP工作目录
			if(dirAry != null && dirAry.length > 0)
				changeDir(dirAry);
			
			//3、删除文件
			return ftpClient.deleteFile(fileName);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(ftpClient != null)
					ftpClient.disconnect();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * 从FTP根目录下载文件
	 * @author beck 
	 * @date 2016-09-22
	 * @param 	fileName		文件名
	 * @param 	outputStream	输出流
	 * @return	boolean			是否下载成功
	 */
	public static boolean downloadFile(String fileName, OutputStream outputStream)
	{
		return downloadFile(fileName, outputStream);
	}
	
	/**
	 * 从指定目录下载文件
	 * @author beck 
	 * @date 2016-09-22
	 * @param 	fileName		文件名
	 * @param 	outputStream	输出流
	 * @param	dirAry			目录数组
	 * @return	boolean			是否下载成功
	 * 注：dirAry为层级目录，非同一级别多个目录，例如：数组中有三个元素【aa】、【bb】、【cc】，
	 * 则从FTP服务器上的aa下的bb下的cc目录下进行下载
	 */
	public static boolean downloadFile(String fileName, OutputStream outputStream, String... dirAry)
	{
		try
		{
			//1、初始化FTPClient
			ftpClient = initFtpClient();
			if(ftpClient == null)
				return false;
			
			//2、切换FTP工作目录
			if(dirAry != null && dirAry.length > 0)
				changeDir(dirAry);
			
			//3、下载文件到对应的输出流
			return ftpClient.retrieveFile(fileName, outputStream);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				outputStream.close();
				if(ftpClient != null)
					ftpClient.disconnect();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * 从FTP根目录获取文件名列表
	 * @author beck 
	 * @date 2014-12-2 
	 * @return	List<String>	文件名列表
	 */
	public static List<String> getFileNameList()
	{
		return getFileNameList();
	}
	
	/**
	 * 
	 * 从指定目录获取文件名列表
	 * @author beck 
	 * @date 2014-12-2 
	 * @param 	dirAry			目录数组
	 * @return	List<String>	文件名列表
	 * 注：dirAry为层级目录，非同一级别多个目录，例如：数组中有三个元素【aa】、【bb】、【cc】，
	 * 则从FTP服务器上的aa下的bb下的cc目录获取文件名列表
	 */
	public static List<String> getFileNameList(String... dirAry)
	{
		List<String> fileNameList = new ArrayList<String>();
		try
		{
			//1、初始化FTPClient
			ftpClient = initFtpClient();
			if(ftpClient == null)
				return null;
			
			//2、切换FTP工作目录
			if(dirAry != null && dirAry.length > 0)
				changeDir(dirAry);
			
			//3、获取文件名列表
			FTPFile[] ftpFileAry = ftpClient.listFiles();
			for(FTPFile ftpFile : ftpFileAry)
				fileNameList.add(ftpFile.getName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(ftpClient != null)
					ftpClient.disconnect();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return fileNameList;
	}
	
	/**
	 * 创建目录
	 * @author beck 
	 * @date 2014-11-23 
	 * @param 	dirAry		目录数组
	 * @return	boolean		目录是否创建成功
	 */
	private static boolean mkDirs(String... dirAry)
	{
		try
		{
			//1、如果FTPClient未初始化，先进行初始化
			if(ftpClient == null)
				initFtpClient();
			
			//2、创建目录
			for(String dir : dirAry)
			{
				if(StringUtils.isNotBlank(dir))
				{
					ftpClient.mkd(dir);
					ftpClient.changeWorkingDirectory(dir);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 切换FTP工作目录
	 * @author beck 
	 * @date 2014-11-23 
	 * @param dirAry	目录数组
	 */
	private static void changeDir(String... dirAry)
	{
		try
		{
			for(String dir : dirAry)
			{
				if(StringUtils.isNotBlank(dir))
					ftpClient.changeWorkingDirectory(dir);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化FTPClient
	 * @author beck 
	 * @date 2016-09-22
	 * @return	FTPClient		初始化FTPClient对象
	 */
	private static FTPClient initFtpClient()
	{
		try
		{
			//1、初始化连接参数
			FTPClient ftpClient = new FTPClient();
			InetAddress inetAddress = InetAddress.getByName(host);
			ftpClient.connect(inetAddress, port);
			
			//2、测试连接
			int replyCode = ftpClient.getReplyCode();
			if(!FTPReply.isPositiveCompletion(replyCode))
			{
				log.error("FTP连接异常！");
				return null;
			}
			
			//3、验证登录
			if(!ftpClient.login(username, password))
			{
				log.error("FTP登录失败！");
				return null;
			}
			
			//设置文件传送类型
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			return ftpClient;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return the host
	 */
	public static String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public static void setHost(String host) {
		FTPClientUtil.host = host;
	}

	/**
	 * @return the port
	 */
	public static int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public static void setPort(int port) {
		FTPClientUtil.port = port;
	}

	/**
	 * @return the username
	 */
	public static String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public static void setUsername(String username) {
		FTPClientUtil.username = username;
	}

	/**
	 * @return the password
	 */
	public static String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public static void setPassword(String password) {
		FTPClientUtil.password = password;
	}
	
}