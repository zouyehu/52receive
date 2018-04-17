package com.winnerlook.framework.kafka.dao;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import kafka.consumer.KafkaStream;

/**
 * kafka dao层
 * @author Hu
 *
 */
public interface KafkaDao {
	
	/**
	 * 发送数据到指定主题
	 * @param topic 主题，表示一类消息
	 * @param data 数据
	 */
	void send(String topic, String data);
	
	/**
	 * 发送数据到指定主题，同时指定分区
	 * @param topic 主题
	 * @param partition 所在分区，从0开始，范围：0 <= partition < 当前主题所有分区数
	 * @param data 数据
	 */
	void send(String topic, int partition, String data);
	
	/**
	 * 获取KafkaConsumer，订阅方式消费消息，可同时订阅多个主题，使用后须调用close方法释放资源
	 * @return
	 */
	KafkaConsumer<String, String> getKafkaConsumer();
	
	/**
	 * 获取KafkaConsumer，非订阅方式消费消息，针对单个主题，使用后须调用close方法释放资源
	 * @return
	 */
	KafkaConsumer<String, String> getKafkaConsumerNoGroup();
	
	/**
	 * 创建主题，指定分区、复制数及相关属性
	 * @param topic
	 * @param partitions
	 * @param replications
	 * @param properties
	 */
	void createTopic(String topic, int partitions, int replications, Properties properties);
	
	/**
	 * 创建主题，指定分区、复制数
	 * @param topic
	 * @param partitions
	 * @param replications
	 */
	void createTopic(String topic, int partitions, int replications);
	
	/**
	 * 删除主题
	 * @param topic 主题
	 */
	void deleteTopic(String topic);
	
	/**
	 * 主题是否存在
	 * @param topic 主题
	 * @return
	 */
	boolean topicExists(String topic);
	
}
