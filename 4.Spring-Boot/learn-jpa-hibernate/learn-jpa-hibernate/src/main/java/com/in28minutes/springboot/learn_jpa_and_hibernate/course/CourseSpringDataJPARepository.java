package com.in28minutes.springboot.learn_jpa_and_hibernate.course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSpringDataJPARepository extends JpaRepository<Course, Long>{

	List<Course> findAllByAuthor(String author);
	List<Course> findAllByName(String name);
}
