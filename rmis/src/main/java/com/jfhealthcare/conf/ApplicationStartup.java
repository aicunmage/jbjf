package com.jfhealthcare.conf;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.aliyun.openservices.ons.api.Consumer;

@Service
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private MQConfig mQConfig;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		 if(event.getApplicationContext().getParent() == null){
			 Consumer consumer = mQConfig.createConsumer();
			 consumer.start();
		 }
	}

}
