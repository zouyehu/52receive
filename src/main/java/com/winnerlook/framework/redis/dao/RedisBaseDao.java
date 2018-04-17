package com.winnerlook.framework.redis.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis基类Dao接口 
 */
public interface RedisBaseDao 
{
	/** ===============Redis-String数据结构接口START=============== */
	/**
	 * 设置字符串
	 * @author majun 
	 * @date 2014-9-28 
	 * @param 	key			key	
	 * @param 	value		字符串值
	 */
	void set(String key, String value);
	
	/**
	 * redis for 自动增长
	 * @param key
	 * @param value
	 */
	public void setStr(String key, String value);
	
	/**
	 * 设置字符串(含过期时间)
	 * @author majun 
	 * @date 2014-9-28 
	 * @param key			key
	 * @param value			字符串值
	 * @param timeout		过期时间，单位：秒
	 */
	void set(String key, String value, long timeout);
	
	boolean setIfAbsent(String key, String value, long timeout);
	
	/**
	 * 设置多个字符串
	 * @author majun 
	 * @date 2014-9-28 
	 * @param paramMap	字符串Map
	 */
	void mset(Map<String, String> paramMap);
	
	/**
	 * 根据key获取字符串		
	 * @author majun 
	 * @date 2014-9-28 
	 * @param 	key			key
	 * @return	String		key对应的字符串值
	 */
	String get(String key);
	
	public String getStr(String key);
	
	/**
	 * 根据key列表获取字符串列表
	 * @author majun 
	 * @date 2014-9-28 
	 * @param 	keys				key列表
	 * @return	List<String>		key列表对应的字符串列表
	 */
	List<String> mget(List<String> keys);
	
	/**
	 * 设置对象(采用Redis的String存储)
	 * @author majun 
	 * @date 2014-9-28 
	 * @param key	key
	 * @param T		对象
	 */
	<T extends Serializable> void setObject(String key, T T);
	
	/**
	 * 设置对象(采用Redis的String存储，含过期时间)
	 * @author majun 
	 * @date 2014-9-28 
	 * @param key	key
	 * @param T		对象
	 * @param timeout		过期时间，单位：秒
	 */
	<T extends Serializable> void setObject(String key, T T, long timeout);
	
	/**
	 * 设置多key的同一类型对象集合(采用Redis的String存储)
	 * @author majun 
	 * @date 2014-9-28 
	 * @param paramMap		参数Map
	 */
	<T extends Serializable> void msetObject(Map<String, T> paramMap);
	
	/**
	 * 根据key获取对象(对象采用Redis的String存储)
	 * @author majun 
	 * @date 2014-9-28 
	 * @param 	key		key
	 * @return	T		对象
	 */
	<T extends Serializable> T getObject(String key);
	
	/**
	 * 根据key列表获取同一对象列表(列表中的对象采用Redis的String存储)
	 * @author majun 
	 * @date 2014-9-28 
	 * @param 	keys		keys列表
	 * @return	List<T>		对象列表		
	 */
	<T extends Serializable> List<T> mgetObject(List<String> keys);
	/** ===============Redis-String数据结构接口END=============== */
	
	/** ===============Redis-Hash数据结构接口START=============== */
	/**
	 * Hash设置(可用于保存对象或者保存对象的单个field)
	 * @author majun 
	 * @date 2014-9-28 
	 * @param key		Hash表的key
	 * @param field		Hash表中的域field
	 * @param T			对象
	 */
	<T extends Serializable> void hset(String key, String field, T T);
	
	/**
	 * Hash批量设置(可用于保存多个对象或者保存单个对象的多个field)
	 * @author majun 
	 * @date 2014-9-28 
	 * @param key			Hash表的key
	 * @param paramMap		Hash表的field和Value组成的Map
	 */
	<T extends Serializable> void hmset(String key, Map<String, T> paramMap);
	
	/**
	 * 根据Hash表的key和域Field获取对应的Value(可用于获取对象或者获取对象的单个field)
	 * @author majun 
	 * @date 2014-9-28 
	 * @param 	key				Hash表的key
	 * @param 	field			Hash表中的域field
	 * @return	T				Hash的key和域Field获取对应的Value
	 */
	<T extends Serializable> T hget(String key, String field);
	
	/**
	 * 根据Hash表的key和域Field列表获取对应的Value列表(可用于获取多个对象或者对象的多个属性)
	 * @author majun 
	 * @date 2014-9-28 
	 * @param 	key			Hash表的key
	 * @param 	fields		域Field列表
	 * @return	List<T>		Hash的key和域Field列表获取对应的Value列表
	 */
	<T extends Serializable> List<T> hmget(String key, List<String> fields);
	
	/**
	 * 根据Hash表的key获取对应的所有对象(可用于获取多个对象)
	 * @author majun 
	 * @date 2014-9-28 
	 * @param 	key					Hash表的key
	 * @return	Map<String, T>		Hash表的key对应的所有对象Map
	 */
	<T> Map<String, T> hgetAll(String key); 
	
	/**
	 * 根据Hash表的key和域Field进行删除
	 * @author majun 
	 * @date 2014-9-28 
	 * @param key		Hash表的key
	 * @param fields	Hash表对应域Field数组
	 */
	<T> void hDel(String key, Object... fields);
	/** ===============Redis-Hash数据结构接口END=============== */
	
	/** ===============Redis接口START=============== */
	/**
	 * 设置key在多少秒后过期
	 * @author majun 
	 * @date 2014-9-28 
	 * @param 	key			key
	 * @param	timeout		过期时间
	 * @return	boolean		是否设置成功
	 */
	boolean expire(String key, long timeout);
	
	/**
	 * 设置key在固定的某个时刻后过期
	 * @author majun 
	 * @date 2014-9-28 
	 * @param 	key			key
	 * @param 	date		固定的某个时刻
	 * @return	boolean		是否设置成功
	 */
	boolean expireAt(String key, Date date);
	
	/**
	 * 根据key删除单个对象
	 * @author majun 
	 * @date 2014-9-28 
	 * @param key	Redis中存储的key
	 */
	void delete(String key);
	
	/**
	 * 根据key列表删除多个对象
	 * @author majun 
	 * @date 2014-9-28 
	 * @param keys	keys列表
	 */
	void deleteAll(List<String> keys);
	/** ===============Redis接口END=============== */
	
	/** 
     * 获取自增索引 
     * @param key 
     * @return 
     */  
	public Integer  incrementAndGet(String key, int count);
	
	/**
	 * 模糊查找redis中的key，使用通配符，如*
	 * @author shibaorui
	 * @date 2015-10-10
	 * @param pattern
	 * @return
	 */
	Set<String> keys(String pattern);
	
	/**
	 * @说明：队列发送
	 * @Auther CY
	 * @date 2016年4月15日 上午10:35:31
	 * @param channel
	 * @param message
	 */
	public void sendMsg(String channel, Object message);
	
	/**
	 * @说明：获取队列缓冲值
	 * @Auther CY
	 * @date 2016年4月18日 下午3:07:37
	 * @param key
	 * @return
	 */
	public Long getMQLength(String key);
	
	/** 
	 * @说明：接收队列消息 
	 * @Auther CY
	 * @date 2016年4月18日 下午3:08:29
	 * @param key
	 * @return
	 */
	public Object getMsg(String key);
	
	/**
	 * @说明：redis发布订阅
	 * @Auther Beck
	 * @date 2016年9月22日 下午3:07:37
	 * @param key
	 * @return
	 */
	public void publish(String channel, Object obj);
}
