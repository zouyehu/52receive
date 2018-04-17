package com.winnerlook.framework.ehcache.dao.impl;

import java.util.Collection;

import com.winnerlook.framework.ehcache.dao.EhCacheDao;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class EhCacheDaoImpl implements EhCacheDao
{
	
	private Cache cacheFactory;

	public Cache getCacheFactory() {
		return cacheFactory;
	}

	public void setCacheFactory(Cache cacheFactory) {
		this.cacheFactory = cacheFactory;
	}

	@Override
	public void put(String key, Object value) {
		Element element = new Element(key, value);
		cacheFactory.put(element);
	}

	@Override
	public void put(String key, Object value, int timeToIdleSeconds, int timeToLiveSeconds) {
		Element element = new Element(key, value, timeToIdleSeconds, timeToLiveSeconds);
		cacheFactory.put(element);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key) {
		Element element = cacheFactory.get(key);
		return element == null ? null : (T) element.getObjectValue();
	}

	@Override
	public boolean remove(String key) {
		return cacheFactory.remove(key);
	}

	@Override
	public void removeAll(Collection<?> keys) {
		cacheFactory.removeAll(keys);
	}

	@Override
	public void removeAll() {
		cacheFactory.removeAll();
	}
	
	@Override
	public void evictExpiredElements() {
		cacheFactory.evictExpiredElements();
	}
	
	
}
