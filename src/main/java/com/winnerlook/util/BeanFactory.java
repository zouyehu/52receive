package com.winnerlook.util; 

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Copyright 北京瑞友科技股份有限公司上海分公司-2016
 * @说明：基础类构造
 * @Auther CY
 * @date 2016年3月24日 上午9:17:00
 * =================Modify Record=================
 * @Modifier			@date			@Content
 *
 */
public class BeanFactory {
	
	private static BeanFactory instance;
	
	private BeanFactory(){}
	
	/**
	 * 单例构造工厂
	 * @说明：
	 * @Auther CY
	 * @date 2016年3月24日 上午9:56:09
	 * @return
	 */
	public synchronized static BeanFactory getInstance(){
		if(null==instance)
			instance = new BeanFactory();
		return instance;
	}
	
	/**
	 * 
	 * @说明：获取返回结果集 map
	 * @Auther CY
	 * @date 2016年3月24日 上午9:17:46
	 * @return
	 */
	public Map<String,Object> getResultMap(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", 0);
		map.put("info", "");
		return map;
	}

	/**
	 * @说明：map排序组成String
	 * @Auther CY
	 * @date 2016年3月24日 上午9:31:10
	 * @param paramsMap
	 * @return
	 */
    public String sortsMap(Map<String, Object> paramsMap) {
    	Iterator<String> it = paramsMap.keySet().iterator();
    	List<String> values = new ArrayList<String>();
		while(it.hasNext()){
			values.add((String)paramsMap.get(it.next()));
		}
        Collections.sort(values);

        StringBuffer sbuff = new StringBuffer("");
        for (String val : values) {
        	sbuff.append(val);
        }

        return sbuff.toString();
    }
    
}
