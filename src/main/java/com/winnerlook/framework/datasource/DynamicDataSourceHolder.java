package com.winnerlook.framework.datasource;

/**
 * 动态数据源持有者 
 * =================Modify Record=================
 * @Modifier			@date			@Content
 *  	新增
 */
public class DynamicDataSourceHolder 
{
	private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	
	/**
	 * 设置数据源的对应的key
	 * @author majun 
	 * @date 2014-9-28 
	 * @param key			数据源的对应的key(数据源枚举常量类中对应的key)
	 */
	public static void setDataSourceKey(String key)
	{
		threadLocal.set(key);
	}
	
	/**
	 * 获取数据源对应的key
	 * @author majun 
	 * @date 2014-9-28 
	 * @return	String		数据源对应的key
	 */
	public static String getDataSourceKey()
	{
		return threadLocal.get();
	}
}
