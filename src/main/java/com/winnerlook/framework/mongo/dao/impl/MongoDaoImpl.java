package com.winnerlook.framework.mongo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.winnerlook.framework.mongo.dao.MongoDao;

/**
 * mongo-dao实现
 * 
 *@auther Hu
 *@date 2017-2-16
 */
public class MongoDaoImpl implements MongoDao {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void insert(Object object) {
		mongoTemplate.insert(object);
	}

	@Override
	public void insert(Object object, String collectionName) {
		mongoTemplate.insert(object, collectionName);
	}

	@Override
	public void insertAll(List<?> list) {
		mongoTemplate.insertAll(list);
	}

	@Override
	public List<?> find(Query query,
			Class<?> entityClass) {
		return mongoTemplate.find(query, entityClass);
	}

	@Override
	public List<?> find(Query query,
			Class<?> entityClass, String collectionName) {
		return mongoTemplate.find(query, entityClass, collectionName);
	}

	@Override
	public Object findOne(Query query, Class<?> entityClass) {
		return mongoTemplate.findOne(query, entityClass);
	}

	@Override
	public Object findOne(Query query,
			Class<?> entityClass, String collectionName) {
		return mongoTemplate.findOne(query, entityClass, collectionName);
	}

	@Override
	public void remove(Query query, Class<?> entityClass) {
		mongoTemplate.remove(query, entityClass);
	}

	@Override
	public void remove(Query query, String collectionName) {
		mongoTemplate.remove(query, collectionName);
	}

	@Override
	public void remove(Query query, Class<?> entityClass,
			String collectionName) {
		mongoTemplate.remove(query, entityClass, collectionName);
	}

	@Override
	public Object findAndRemove(Query query,
			Class<?> entityClass) {
		return mongoTemplate.findAndRemove(query, entityClass);
	}

	@Override
	public Object findAndRemove(Query query,
			Class<?> entityClass, String collectionName) {
		return mongoTemplate.findAndRemove(query, entityClass, collectionName);
	}

	
}
