package com.winnerlook.framework.datasource;

/**
 * 
 * 数据源枚举常量类
 * =================Modify Record=================
 * @Modifier			@date			@Content
 *   新增
 */
public enum DataSourceEnum 
{
	/** 主库数据源 */
	MASTER("master", "主库数据源"),
	/** send数据源 */
	SEND("send", "发送数据源"),
	
	/** mas数据源 */
	MAS("mas", "mas数据源"),
	
	/** mycat数据源 */
	MYCAT("mycat", "mycat数据源"),
	
	/** 活动数据源 */
	EVENT("event", "活动数据源"),
	/** 预留数据源 */
	RESERVE("reserve", "预留数据源"),
	/**slave数据源*/
	SLAVE("slave", "slave数据源"),
	/**天猫订单消息数据源*/
	SYSINFO("sysInfo","天猫订单消息数据源");
	
	private DataSourceEnum(String key, String description) 
	{
		this.key = key;
		this.description = description;
	}
	
	/** 数据源对应的key(用于在Spring配置文件中指定数据源Map中的key使用) */
	private String key;
	
	/** 说明 */
	private String description;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
