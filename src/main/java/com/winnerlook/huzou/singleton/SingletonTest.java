package com.winnerlook.huzou.singleton;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 懒汉式单例、饿汉式单例
 * 1、单例类只能有一个实例。
　* 2、单例类必须自己创建自己的唯一实例。
 *　3、单例类必须给所有其他对象提供这一实例。
 * @author Hu
 *
 */
public class SingletonTest {
	
	//静态内部类
	private static class lazyHolder{
		private static final SingletonTest singleton = new SingletonTest();
	}
	
	private SingletonTest(){};
	
	//静态内部类初始化
//	public static final SingletonTest getInstance(){
//		return lazyHolder.singleton;
//	}
	
	private static SingletonTest singleton = null;
	////饿汉式单例，不考虑线程安全.考虑线程安全，加上同步synchronized关键字，性能低
	public static SingletonTest getInstance(){
		if(singleton == null){
			singleton = new SingletonTest();
		}
		return singleton;
	}
	
	//或者双重检查锁定,确保了只有第一次调用单例的时候才会做同步，这样也是线程安全的，同时避免了每次都同步的性能损耗
	/*public static SingletonTest getInstance(){
		if(singleton == null){
			synchronized (SingletonTest.class) {
				if(singleton == null){
					singleton = new SingletonTest();
				}
			}
		}
		return singleton;
	}*/
	
	
	//饿汉式单例类.在类初始化时，已经自行实例化
	/*private static final Singleton single = new Singleton();  
    //静态工厂方法   
    public static Singleton getInstance() {  
        return single;  
    }  */

	public String test(){
		String yj_userCode = "";
		try {
			Properties props = PropertiesLoaderUtils.loadAllProperties("properties/sendCode.properties");
			yj_userCode = props.getProperty("yj_userCode");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return yj_userCode;
	}
	
	public static void main(String[] args) {
//		SingletonTest singtest = SingletonTest.getInstance();
		SingletonTest singtest = new SingletonTest();
		
		System.out.println(singtest.test());
	}
}
