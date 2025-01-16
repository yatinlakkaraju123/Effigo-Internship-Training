package com.in28minutes.springboot.learn_jpa_and_hibernate.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.jdbc.CourseJDBCRepository;
@Component
public class CourseJPACommandLineRunner implements CommandLineRunner{
	//@Autowired
	//private CourseJPARepository repository;
	@Autowired
	private CourseSpringDataJPARepository repository;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method 
		repository.save(new Course(1,"Java","Sachin"));
		repository.save(new Course(2,"Hava","qachin"));
		repository.save(new Course(3,"Lava","cachin"));
		//repository.insert(new Course(1,"Java","Sachin"));
		//repository.insert(new Course(2,"Hava","qachin"));
		//repository.insert(new Course(3,"Lava","cachin"));
		repository.deleteById(1l);
		System.out.println(repository.findById(2l));
		System.out.println(repository.findAll());
		System.out.println(repository.findAllByAuthor("cachin"));
		System.out.println(repository.findAllByName("Hava"));
	}
	

}
