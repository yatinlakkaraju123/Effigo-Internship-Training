package com.myprojects.OneToMany.Student.Repository;

import com.myprojects.OneToMany.Student.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
