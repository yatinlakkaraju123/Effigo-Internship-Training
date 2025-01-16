package com.in28minutes.learn_spring_framework.examples.d1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

//import com.in28minutes.learn_spring_framework.game.GameRunner;
//import com.in28minutes.learn_spring_framework.game.GamingConsole;

@Component
class ClassA{
	
}
@Component
@Lazy
class ClassB{
	private ClassA classA;
	ClassB(ClassA classA)
	{	System.out.println("this is class b");
		this.classA = classA;
	}
	public void doSomething() {
		System.out.println("Class B is doing something");
	}
}




@Configuration
@ComponentScan
public class LazyInitializationLauncherApplication {
	
	
	public static void main(String[] args) {
		try(var context = new AnnotationConfigApplicationContext(LazyInitializationLauncherApplication.class);)
		{
			//Arrays.stream(context.getBeanDefinitionNames()).forEach(bean->System.out.println(bean));
			System.out.println("context initialization is done");
			context.getBean(ClassB.class).doSomething();
		}
		
		

	}

}
