package com.winnerlook.framework.kafka.dao.impl;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.I0Itec.zkclient.ZkClient;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.security.JaasUtils;
import org.springframework.kafka.core.KafkaTemplate;

import com.winnerlook.framework.kafka.dao.KafkaDao;
import com.winnerlook.util.ApplicationContextUtil;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;
import kafka.utils.ZKStringSerializer$;

/**
 * kafka 实现层
 * @author Hu
 */
public class KafkaDaoImpl implements KafkaDao {

	private KafkaTemplate<?, String> kafkaTemplate;
	
	private ZkClient zkClient;
	
	private ZkUtils zkUtils;
	
	@PostConstruct
	public void initZkUtil(){
		zkClient.setZkSerializer(ZKStringSerializer$.MODULE$);
		zkUtils = ZkUtils.apply(zkClient, JaasUtils.isZkSecurityEnabled());
	}
	
	@Override
	public void send(String topic, String data) {
		kafkaTemplate.send(topic, data);
	}

	@Override
	public void send(String topic, int partition, String data) {
		kafkaTemplate.send(topic, partition, data);
	}
	
	@Override
	public KafkaConsumer<String, String> getKafkaConsumer() {
		return getKafkaConsumer("consumerSubscribe");
	}

	@Override
	public KafkaConsumer<String, String> getKafkaConsumerNoGroup() {
		return getKafkaConsumer("consumer");
	}

	@SuppressWarnings("unchecked")
	private KafkaConsumer<String, String> getKafkaConsumer(String id){
		return (KafkaConsumer<String, String>) ApplicationContextUtil
				.getBean(id, KafkaConsumer.class);
	}
	
	@Override
	public void deleteTopic(String topic) {
		AdminUtils.deleteTopic(zkUtils, topic);
	}

	@Override
	public void createTopic(String topic, int partitions, int replications, Properties properties) {
		AdminUtils.createTopic(zkUtils, topic, partitions, replications, properties, RackAwareMode.Enforced$.MODULE$);
	}

	@Override
	public void createTopic(String topic, int partitions, int replications) {
		createTopic(topic, partitions, replications, new Properties());
	}

	@Override
	public boolean topicExists(String topic) {
		return AdminUtils.topicExists(zkUtils, topic);
	}

	public void setKafkaTemplate(KafkaTemplate<?, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void setZkClient(ZkClient zkClient) {
		this.zkClient = zkClient;
	}

}
