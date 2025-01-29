package com.myProjects.ManyToOne.Student.Repository;

import com.myProjects.ManyToOne.Student.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
