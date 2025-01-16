package com.in28minutes.learn_spring_framework.examples.g1;

//import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//import com.in28minutes.learn_spring_framework.game.GameRunner;
//import com.in28minutes.learn_spring_framework.game.GamingConsole;
@Component
class BusinessClass{
	
	Dependency1 dependency1;
	
	
	//@Autowired
	public void setDependency1(Dependency1 dependency1) {
		System.out.println("Setter Injection - setDependency1");
		this.dependency1 = dependency1;
	}
	//@Autowired
	public void setDependency2(Dependency2 dependency2) {
		System.out.println("Setter Injection - setDependency2");
		this.dependency2 = dependency2;
	}
	
	Dependency2 dependency2;
	
	@Autowired
	public BusinessClass(Dependency1 dependency1, Dependency2 dependency2) {
		super();
		System.out.println("constructor injection");
		
		this.dependency1 = dependency1;
		this.dependency2 = dependency2;
	}
	public String toString()
	{
		return "Using" + dependency1 + "and" + dependency2;
	}
}
@Component
class Dependency1{
	
}
@Component
class Dependency2{
	
}
@Configuration
@ComponentScan
public class CDIContextLauncherApplication {
	
	
	public static void main(String[] args) {
		try(var context = new AnnotationConfigApplicationContext(CDIContextLauncherApplication.class);)
		{
			//Arrays.stream(context.getBeanDefinitionNames()).forEach(bean->System.out.println(bean));
			System.out.println(context.getBean(BusinessClass.class).toString());
		}
		
		

	}

}
