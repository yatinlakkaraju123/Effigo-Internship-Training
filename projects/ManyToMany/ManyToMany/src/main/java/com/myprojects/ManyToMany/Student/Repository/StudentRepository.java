package com.myprojects.ManyToMany.Student.Repository;

import com.myprojects.ManyToMany.Student.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
