package com.winnerlook.huzou.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.winnerlook.huzou.test.MongodbTest;

public class StartupListener implements ApplicationListener<ApplicationEvent> {
	
	@Autowired
	private MongodbTest mongodbTest;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		
//		mongodbTest.run();
	}

}
