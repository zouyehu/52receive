package com.winnerlook.framework.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源抽象实现类(获取数据源对应的key) 
 * =================Modify Record=================
 * @Modifier			@date			@Content
 *   新增
 */
public class DynamicDataSource extends AbstractRoutingDataSource 
{
	@Override
	protected Object determineCurrentLookupKey() 
	{
		return DynamicDataSourceHolder.getDataSourceKey();
	}
}
