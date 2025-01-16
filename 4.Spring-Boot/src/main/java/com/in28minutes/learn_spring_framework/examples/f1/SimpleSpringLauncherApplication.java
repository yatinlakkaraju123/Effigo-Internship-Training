package com.in28minutes.learn_spring_framework.examples.f1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
@Component
class BusinessService{
	private DataService dataservice;
	
	public DataService getDataservice() {
		
		return dataservice;
	}
	@Autowired
	public void setDataservice(DataService dataservice) {
		System.out.println("setter injection in business service");
		this.dataservice = dataservice;
	}
}
@Component
class DataService{
	
}

@Configuration
@ComponentScan
public class SimpleSpringLauncherApplication {
	
	
	public static void main(String[] args) {
		try(var context = new AnnotationConfigApplicationContext(SimpleSpringLauncherApplication.class);)
		{
			Arrays.stream(context.getBeanDefinitionNames()).forEach(bean->System.out.println(bean));
			
		}
		
		

	}

}
