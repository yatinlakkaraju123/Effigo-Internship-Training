package com.in28minutes.learn_spring_framework.examples.h1;


import java.util.Arrays;

import org.springframework.context.support.ClassPathXmlApplicationContext;




public class XMLConfigurationLauncherApplication {
	
	
	public static void main(String[] args) {
		try(var context = new ClassPathXmlApplicationContext("ContextConfiguration.xml");)
		{
			Arrays.stream(context.getBeanDefinitionNames()).forEach(bean->System.out.println(bean));
			System.out.println(context.getBean("name"));
		}
		
		

	}

}
