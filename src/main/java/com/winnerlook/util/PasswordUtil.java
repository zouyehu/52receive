package com.winnerlook.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;


/**
 * @Copyright 北京瑞友科技股份有限公司上海分公司-2016
 * @说明：密码的加密和解密 sha1方式
 * @Auther ligang
 * @date 2016年4月8日 下午5:42:24
 * =================Modify Record=================
 * @Modifier			@date			@Content
 */
public class PasswordUtil {
	
	/* 
	* 16进制数字字符集 
	*/ 
	private static String hexString="0123456789ABCDEF"; 
	
	/**
	 * @说明：密码的sha1加密
	 * @author ligang
	 * @throws UnsupportedEncodingException 
	 * @date 2016年4月8日 下午5:42:24
	 * @param password 用户密码
	 */
	public static  String encodePW(String password) throws UnsupportedEncodingException{
		String sPassword = password.trim();
		byte[] bytesArray = sPassword.getBytes("UTF-8");
		String sPwdEncode = "";
		byte[] SHA1result;
		String sTemp;
		String sha1Hex = DigestUtils.sha1Hex(bytesArray).toUpperCase();
//		System.err.println("sha1加密后："+sha1Hex);
		SHA1result = sha1Hex.getBytes("UTF-8");
		for (int i = 0; i < SHA1result.length; i++) {
			sTemp = Integer.toHexString(SHA1result[i] & 0xFF); 
			if(sTemp.length()==1){
				sTemp = "0" + sTemp;
			}
			sPwdEncode += sTemp; 
		}
		sPassword = sPwdEncode.toUpperCase();
		return sPassword;
	}
	
	/**
	 * @说明：获取用户密码的sha1加密后的值
	 * 将16进制数字解码成字符串,适用于所有字符（包括中文） 
	 * @author ligang
	 * @throws UnsupportedEncodingException 
	 * @date 2016年4月8日 下午5:42:24
	 * @param bytes 用户数据库保存的密码
	 */
	public static String decodePW(String bytes) 
	{ 
	ByteArrayOutputStream baos=new ByteArrayOutputStream(bytes.length()/2); 
	//将每2位16进制整数组装成一个字节 
	for(int i=0;i<bytes.length();i+=2) {
		baos.write((hexString.indexOf(bytes.charAt(i))<<4 |hexString.indexOf(bytes.charAt(i+1)))); 
	}
	
	return new String(baos.toByteArray()); 
	} 
	
	public static void main(String[] args) {
		
		try {
			System.out.println("数据库保存："+encodePW("admin"));
//			System.err.println("反解sha1密码："+decodePW(encodePW("admin")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
}
