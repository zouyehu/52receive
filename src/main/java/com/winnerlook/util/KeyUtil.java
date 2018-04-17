package com.winnerlook.util;

import java.util.Random;
import java.util.UUID;

/**
 * @Copyright 北京瑞友科技股份有限公司上海分公司-2016
 * @说明：主键生成工具
 * @Auther ChangYi
 * @date 2016年3月23日 下午3:20:18
 * =================Modify Record=================
 * @Modifier			@date			@Content
 */
public class KeyUtil {


	/**
	 * @说明：获取主键
	 * @Auther ChangYi
	 * @date 2016年3月23日 下午3:26:18
	 * @return String
	 */
	public static String getKey(){
		
		UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
	}
	
	public static String getKey(String customerId){
		return customerId + System.currentTimeMillis()
		+ new Random().nextInt(1000);
	}
}
