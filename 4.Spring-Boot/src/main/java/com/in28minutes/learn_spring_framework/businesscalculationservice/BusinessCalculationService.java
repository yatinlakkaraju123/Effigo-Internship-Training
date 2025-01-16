package com.in28minutes.learn_spring_framework.businesscalculationservice;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class BusinessCalculationService {
	MongoDBDataservice mongodb;
	MySQLDataService mysql;
	
	public BusinessCalculationService(MongoDBDataservice mongodb, MySQLDataService mysql) {
		super();
		this.mongodb = mongodb;
		this.mysql = mysql;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(var context = new AnnotationConfigApplicationContext(BusinessCalculationService.class))
		{
			System.out.println(Arrays.stream(context.getBean(DataService.class).retrieve()).max().orElse(0));
		}
		
	}

}
