package com.winnerlook.framework.mybatis.pagination;

import com.winnerlook.framework.base.Page;

/**
 * 数据库方言基类
 * =================Modify Record=================
 * @Modifier			@date			@Content
 * 		新增
 */
public abstract class Dialect 
{
	public static enum DialectType
	{
		MYSQL(MySQLDialect.class), ORACLE(OracleDialect.class);
		
		DialectType(Class<? extends Dialect> clazz)
		{
			this.clazz = clazz;
		}
		
		/** 方言实现类字节码 */
		private Class<? extends Dialect> clazz;
		
		public Class<? extends Dialect> getClazz() {
			return clazz;
		}
		public void setClazz(Class<? extends Dialect> clazz) {
			this.clazz = clazz;
		}
	}
	
	/**
	 * 构造分页SQL
	 * @author majun 
	 * @date 2014-9-28 
	 * @param 	sql			原始SQL语句
	 * @param 	page		分页对象
	 * @return	String		构造后的分页SQL语句
	 */
	public abstract String constructPageSql(String sql, Page<?> page);
}
