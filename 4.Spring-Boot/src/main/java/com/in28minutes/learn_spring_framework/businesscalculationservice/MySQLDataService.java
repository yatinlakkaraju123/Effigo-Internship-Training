package com.in28minutes.learn_spring_framework.businesscalculationservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("MySQLDataserviceQualifier")
@Primary
public class MySQLDataService implements DataService{
	public int[] retrieve() 
	{
		return new int[] {10,20,30,40,50};
	}

}
