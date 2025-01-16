package com.in28minutes.learn_spring_framework.helloworld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

record Person (String name,int age)
{
	
};
record Address(String firstLine,String city) {};
@Configuration
public class HelloWorldConfiguration {
 @Bean
 String name()
	{
		return "Yatin";
	}
 @Bean
 public int age()
 {
	 return 15;
 }
 @Bean
 public Person person()
 {
	 var person = new Person("Yatin",21);
	 return person;
	 
 }
 public Person person2Methodcall()
 {
	 var person = new Person(name(),age());
	 return person;
 }
 public Person person3Parameters(String name,int age)
 {
	 var person = new Person(name,age);
	 return person;
 }
 @Bean(name="address2")
 public Address address()
 {
	 var address = new Address("dayanand nagar","HYD");
	 return address;
 }
}
