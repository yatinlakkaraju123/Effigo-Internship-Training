package com.in28minutes.learn_spring_framework.businesscalculationservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("MongoDBDataserviceQualifier")
public class MongoDBDataservice implements DataService{
	public int[] retrieve() 
	{
		return new int[] {11,21,31,41,51};
	}
}
