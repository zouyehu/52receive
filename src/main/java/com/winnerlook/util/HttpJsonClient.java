package com.winnerlook.util;
import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpJsonClient {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpJsonClient.class);
	
	private CloseableHttpClient client = HttpClients.createDefault();
	private HttpPost post = new HttpPost();
	
	public HttpJsonClient() {
		post.setHeader("Content-Type", "application/json;charset=utf-8");
		post.addHeader("Accept","application/json");
	}
	
	/**
	 * 发送一个post请求，内容为json格式的字符串
	 * @author lizepeng
	 * @date 2016年10月13日
	 * @param url
	 * @param params
	 * @return
	 */
	public String send(String url,  String data) {
		StringEntity strEntity = null;
		try {
			strEntity = new StringEntity(data, "UTF-8");
		} catch (Exception e) {
			logger.error("http请求参数关联异常", e);
			return null;
		}
		return this.send(url, strEntity);
	}
	
	/**
	 * 发送一个post请求，内容实体为httpEntity
	 * @author lizepeng
	 * @date 2016年10月13日
	 * @param url
	 * @param params
	 * @return
	 */
	public String send(String url,  HttpEntity requestEntity) {
		String responseResult = null;
		post.setURI(URI.create(url));
		post.setEntity(requestEntity);
		CloseableHttpResponse response = null;
		try {
			response = client.execute(post);
			HttpEntity responseEntity = response.getEntity();
			if (responseEntity != null) {
				responseResult = EntityUtils.toString(responseEntity, "UTF-8");
			}
		} catch (Exception e) {
			logger.error("http请求异常", e);
			return responseResult;
		} finally {
			post.releaseConnection();
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("响应关闭异常", e);
				}
			}
		}
		return responseResult;
	}
	public static void main(String[] args) {
		HttpJsonClient client = new HttpJsonClient();
		client.send("http://192.168.1.153:8387/winnerrxd/api/trigger/test", "" );
	}
}
