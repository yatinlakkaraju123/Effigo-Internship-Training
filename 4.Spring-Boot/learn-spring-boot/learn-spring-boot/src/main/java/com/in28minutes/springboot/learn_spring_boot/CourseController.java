package com.in28minutes.springboot.learn_spring_boot;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
	//courses
	// Course: id,name,author
	@RequestMapping("/courses")
	public List<Course> retrieveAllCourses()
			{
		return Arrays.asList(
				new Course(1,"Java","yatin"),
				new Course(2,"Python","sachin"),
				new Course(3,"JS","ram")
				
				);
			}
}
