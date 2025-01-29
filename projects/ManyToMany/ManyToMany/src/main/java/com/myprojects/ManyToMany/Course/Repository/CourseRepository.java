package com.myprojects.ManyToMany.Course.Repository;

import com.myprojects.ManyToMany.Course.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
}
