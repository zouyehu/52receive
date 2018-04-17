package com.winnerlook.framework.base; 

import java.io.Serializable;
import java.util.Date;

/**
 * POJO基类对象
 * @author Hu
 * @date 2017-03-29
 */
public class BaseObject implements Serializable 
{
	private static final long serialVersionUID = 329430609755626516L;
	
	/** 删除标识：0、未删除 1、已删除 */
	private int is_del;
	
	/** 创建时间 */
	private Date create_date;

	public int getIs_del() {
		return is_del;
	}

	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

}
