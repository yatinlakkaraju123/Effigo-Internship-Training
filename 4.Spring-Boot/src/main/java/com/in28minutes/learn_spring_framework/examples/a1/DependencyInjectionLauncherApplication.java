package com.in28minutes.learn_spring_framework.examples.a1;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

import com.in28minutes.learn_spring_framework.game.GameRunner;
import com.in28minutes.learn_spring_framework.game.GamingConsole;

@Configuration
@ComponentScan
public class DependencyInjectionLauncherApplication {
	
	
	public static void main(String[] args) {
		try(var context = new AnnotationConfigApplicationContext(DependencyInjectionLauncherApplication.class);)
		{
			Arrays.stream(context.getBeanDefinitionNames()).forEach(bean->System.out.println(bean));
		}
		
		

	}

}
