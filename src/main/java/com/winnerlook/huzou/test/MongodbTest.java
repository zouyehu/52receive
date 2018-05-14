//package com.winnerlook.huzou.test;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import net.sf.json.JSONObject;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Service;
//
//import com.alibaba.fastjson.JSON;
//import com.winnerlook.framework.mongo.dao.MongoDao;
//import com.winnerlook.framework.mongo.dao.impl.MongoDaoImpl;
//
///**
// * mongoDB增删改查测试
// * @author Hu
// *
// */
//@Service
//public class MongodbTest {
//
//	private static final Logger LOG = LoggerFactory.getLogger(MongodbTest.class);
//
//	@Autowired
//	private MongoDao mongoDao;
//
//
//	public class TestMongo extends TimerTask{
//
//		@Override
//		public void run() {
//
//			Map<String,String> map = new HashMap<String, String>();
//			map.put("id", "hu_123");
//			map.put("name", "邹业虎");
//			map.put("password", "123123..0");
//			map.put("address", "松江区");
//			map.put("phone", "13363786261");
//
//			mongoDao.insert(map, "masthird");
//			System.out.println("map格式插入成功！！！");
//
//			JSONObject dataJson = new JSONObject();
//			dataJson.put("wo", "高大");
//			dataJson.put("shi", "威猛");
//			dataJson.put("hu", "屌打");
//			dataJson.put("ge", "服不");
//			mongoDao.insert(dataJson, "masthird");
//			System.out.println("json格式插入成功！！！");
//
//			Query query = Query.query(Criteria.where("address").is("松江区"));
////			Object obj = mongoDao.findOne(query, Map.class,"masthird");
////			System.out.println(JSON.toJSONString(obj));
//			System.out.println(mongoDao.find(query, Map.class, "masthird"));
//		}
//
//	}
//
//	public void run(){
//		Timer timer = new Timer();
//		timer.schedule(new TestMongo(), 3000,30000);
//	}
//}
