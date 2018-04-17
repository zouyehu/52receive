package com.winnerlook.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 通过Spring上下文获取Bean 
 * @Copyright 北京瑞友科技股份有限公司上海分公司-2014
 * @author majun
 * @date 2014-9-28
 * =================Modify Record=================
 * @Modifier			@date			@Content
 * majun				2014-09-28		majun
 */
public class ApplicationContextUtil implements ApplicationContextAware
{
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException 
	{
		ApplicationContextUtil.context = context;
	}
	
	/**
	 * 通过Spring配置文件的Bean对应的ID获取对象示例
	 * @author majun
	 * @date 2014-9-28
	 * @param 	beanId			Spring里配置文件中的Bean对应的ID
	 * @param	clazz			对象Class字节码
	 * @return	T				对象示例
	 */
	public static <T> T getBean(String beanId, Class<T> clazz)
	{
		return context.getBean(beanId, clazz);
	}
	
}
