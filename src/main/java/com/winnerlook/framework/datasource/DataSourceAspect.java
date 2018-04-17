package com.winnerlook.framework.datasource;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;


/**
 * 数据源切面类 
 * =================Modify Record=================
 * @Modifier			@date			@Content
 * 		新增
 */
public class DataSourceAspect 
{
//	private static ILog log = LogMgr.getLogger(DataSourceAspect.class);	
	private static Log log = LogFactory.getLog(DataSourceAspect.class);
			
	/**
	 * 
	 * 在数据库调用之前进行数据源设置
	 * 注：对于数据源设置采取就近原则：
	 * 	1、如果方法上有数据源设置则以方法为准
	 * 	2、如果方法上没有进行数据源设置则以类上的数据源设置为准
	 * @author majun 
	 * @date 2014-9-28 
	 * @param joinPoint		切入点
	 */
	public void before(JoinPoint joinPoint)
	{
		//目标类
		Object target = joinPoint.getTarget();
		//目标类Class字节码
		Class<?> targetClass = target.getClass();
		//目标方法的签名
		Signature signature = joinPoint.getSignature();
		//目标方法名
        String methodName = signature.getName();
        //目标方法的参数类型数组
        Class<?>[] parameterTypes = ((MethodSignature)signature).getMethod().getParameterTypes();
        
        try 
        {
        	DataSource dataSource = null;
            Method method = targetClass.getMethod(methodName, parameterTypes);
            if (method != null && method.isAnnotationPresent(DataSource.class)) //获取方法上的数据源设置
                dataSource = method.getAnnotation(DataSource.class);
            else if(targetClass.isAnnotationPresent(DataSource.class))//获取类上的数据源设置
            	dataSource = targetClass.getAnnotation(DataSource.class);
            
            if(dataSource != null)
            {
            	DataSourceEnum dataSourceName = dataSource.value();
            	String key = dataSourceName.getKey();
                DynamicDataSourceHolder.setDataSourceKey(key);
            }
            else
            	log.info("没有发现对应的数据源，如需设置，请进行设置！");
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
	}
}
