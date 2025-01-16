package com.in28minutes.springboot.learn_jpa_and_hibernate.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.Course;
@Repository
public class CourseJDBCRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static String SQLQUERY = """ 
			insert into course (id,name,author) values (?,?,?);
			
			
			""";
	private static String DeleteQuery="""
			
			delete from course where id = ?
			
			""";
	private static String SELECTQUERY = """
			
			select * from course where id = ?
			""";
	public Course select(long id)
	{	// mapping the result to a bean
		return jdbcTemplate.queryForObject(SELECTQUERY, new BeanPropertyRowMapper<>(Course.class),id);
	}
	public void insert(Course course)
	{
		jdbcTemplate.update(SQLQUERY,course.getId(),course.getName(),course.getAuthor());
	}
	
	public void delete(int id)
	{
		jdbcTemplate.update(DeleteQuery,id);
	}
}
