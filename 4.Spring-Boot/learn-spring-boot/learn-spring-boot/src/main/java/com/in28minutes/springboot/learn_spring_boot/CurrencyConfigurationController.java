package com.in28minutes.springboot.learn_spring_boot;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConfigurationController {
	@Autowired
	private ConfigurationProperties currencyConfigurationController;
	//courses
	// Course: id,name,author
	@RequestMapping("/currency-services")
	public ConfigurationProperties retrieveAllCourses()
			{
		return currencyConfigurationController;
			}
}
