package com.winnerlook.framework.mybatis.pagination;

import com.winnerlook.framework.base.Page;

/**
 * Oracle方言
 * =================Modify Record=================
 * @Modifier			@date			@Content
 * 	新增
 */
public class OracleDialect extends Dialect 
{

	/**
	 * 构造分页SQL
	 * @author majun 
	 * @date 2014-9-28 
	 * @param 	sql			原始SQL语句
	 * @param 	page		分页对象
	 * @return	String		构造后的分页SQL语句
	 */
	@Override
	public String constructPageSql(String sql, Page<?> page) 
	{
		sql = sql.trim();
		
		//当前页数
		int currentPage = page.getCurrentPage();
		//每页显示记录数
		int pageSize = page.getPageSize();
		
		StringBuilder pageSql = new StringBuilder(sql.length() + 100);
		pageSql.append("SELECT * FROM ( SELECT T.*, ROWNUM rn FROM ( ");
		pageSql.append(sql);
		pageSql.append(" ) T WHERE ROWNUM <= " + Page.computeEndPage(currentPage, pageSize));
		pageSql.append(" ) WHERE RN > " + Page.computeStartPage(currentPage, pageSize));
		
		return pageSql.toString();
	}

}
