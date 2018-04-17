package com.winnerlook.util;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @Copyright 北京瑞友科技股份有限公司上海分公司-2016
 * @说明：
 * @Auther CY
 * @date 2016年4月7日 上午10:36:46
 * =================Modify Record=================
 * @Modifier			@date			@Content
 *
 */
public class RequestToBean {  

	private static Logger logger = LoggerFactory.getLogger(RequestToBean.class);
    
    /** 
     * @说明：request转Bean
     * @Auther CY
     * @date 2016年4月7日 上午11:11:50
     * @param request
     * @param t
     * @return
     */
    public static <T> T getBeanToRequeat(HttpServletRequest request,T t){
    	
    	try {
			BeanUtils.populate(t,request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			logger.error("");
			e.printStackTrace();
		} 
    	return t;
    }
} 