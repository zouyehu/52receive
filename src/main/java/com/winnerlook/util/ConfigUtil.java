package com.winnerlook.util;

import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 静态文件配置工具类
 * @author Hu
 * @date 2017-04-12
 *
 */

public class ConfigUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigUtil.class);
	
	//短信发送参数获取配置
	public static String sendConfig(String key){
		String result = "";
		try {
			Properties proper = PropertiesLoaderUtils.loadAllProperties("properties/pushParameter.properties");
			result = proper.getProperty(key);
		} catch (IOException e) {
			LOGGER.error("读取短信发送参数出现异常：{}",e);
		}
		return result;
		
	}

}
