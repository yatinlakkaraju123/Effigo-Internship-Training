package com.myprojects.OneToMany.Course.Repository;

import com.myprojects.OneToMany.Course.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    @Query(value = "select * from course where student_id=?",nativeQuery = true)
    public List<Course> findByStudent(int studentId);
}
