package com.winnerlook.framework.ehcache.dao;

import java.util.Collection;

public interface EhCacheDao {
	/**
	 * 添加元素，相关设置使用ehcache.xml cache节点配置
	 * @param key
	 * @param value 需实现序列化接口
	 */
	void put(String key, Object value);
	
	/**
	 * 添加元素，指定元素的最大闲置时间及存活时间
	 * @param key
	 * @param value 需实现序列化接口
	 * @param timeToIdleSeconds 元素的最大闲置时间(最后访问时间到失效的时间差)，单位秒，若为0，可永久闲置
	 * @param timeToLiveSeconds 元素的最大存活时间(创建到失效的时间差)，单位秒，若为0，可永久存活
	 */
	void put(String key, Object value, int timeToIdleSeconds, int timeToLiveSeconds);
	
	/**
	 * 获取元素
	 * @param key
	 * @return
	 */
	<T> T get(String key);
	
	/**
	 * 删除单个元素
	 * @param key
	 * @return
	 */
	boolean remove(String key);
	
	/**
	 * 批量删除元素
	 * @param keys
	 */
	void removeAll(Collection<?> keys);
	
	/**
	 * 删除所有元素
	 */
	void removeAll();
	
	void evictExpiredElements();
}
