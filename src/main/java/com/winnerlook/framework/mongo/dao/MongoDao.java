package com.winnerlook.framework.mongo.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

/**
 * mongo-dao接口
 * 
 *@auther Hu
 *@date 2017-2-16
 */
public interface MongoDao {

	/**
	 * 根据类型添加
	 * 
	 *@auther zhuming
	 *@date 2017-2-16
	 *@param object		要添加的对象
	 */
	public void insert(Object object);
	
	/**
	 * 添加到指定集合
	 * 
	 *@auther zhuming
	 *@date 2017-2-16
	 *@param object		要添加的对象
	 *@param collectionName		集合名
	 */
	public void insert(Object object,String collectionName);
	
	/**
	 * 批量添加,添加泛型名的集合
	 * 
	 *@auther zhuming
	 *@date 2017-2-16
	 *@param list			批量添加的集合
	 */
	public void insertAll(List<?> list);
	
	/**
	 * 查找
	 * 
	 *@auther zhuming
	 *@date 2017-2-16
	 *@param query			查询条件
	 *@param entityClass	类型
	 *@return
	 */
	public List<?> find(Query query,Class<?> entityClass);
	
	/**
	 * 指定集合查找
	 * 
	 *@auther zhuming
	 *@date 2017-2-16
	 *@param query				查询条件
	 *@param entityClass		类型
	 *@param collectionName		集合名
	 *@return
	 */
	public List<?> find(Query query,Class<?> entityClass,String collectionName);
	
	/**
	 * 查询一个
	 * 
	 *@auther zhuming
	 *@date 2017-2-16
	 *@param query				查询条件
	 *@param entityClass		类型
	 *@return
	 */
	public Object findOne(Query query,Class<?> entityClass);
	
	/**
	 * 指定集合查询一个
	 * 
	 *@auther zhuming
	 *@date 2017-2-16
	 *@param query				查询条件
	 *@param entityClass		类型
	 *@param collectionName		集合名
	 *@return
	 */
	public Object findOne(Query query,Class<?> entityClass,String collectionName);
	
	/**
	 * 删除所有
	 * 
	 *@auther zhuming
	 *@date 2017-2-16
	 *@param query			删除条件
	 *@param entityClass	类型
	 */
	public void remove(Query query,Class<?> entityClass);
	
	/**
	 * 删除所有
	 * 
	 *@auther zhuming
	 *@date 2017-2-16
	 *@param query				删除条件
	 *@param collectionName		集合名
	 */
	public void remove(Query query,String collectionName);
	
	/**
	 * 删除所有
	 * 
	 *@auther zhuming
	 *@date 2017-2-16
	 *@param query				删除条件
	 *@param entityClass		类型
	 *@param collectionName		集合名
	 */
	public void remove(Query query,Class<?> entityClass,String collectionName);
	
	/**
	 * 查找并删除一个
	 * 
	 *@auther zhuming
	 *@date 2017-2-16
	 *@param query				条件
	 *@param entityClass		类型
	 *@return
	 */
	public Object findAndRemove(Query query,Class<?> entityClass);
	
	/**
	 * 查询并删除一个
	 * 
	 *@auther zhuming
	 *@date 2017-2-16
	 *@param query				条件
	 *@param entityClass		类型
	 *@param collectionName		集合名
	 *@return
	 */
	public Object findAndRemove(Query query,Class<?> entityClass,String collectionName);
	
	
	
}
