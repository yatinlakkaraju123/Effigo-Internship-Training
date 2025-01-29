package com.myProjects.ManyToOne.Course.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myProjects.ManyToOne.Student.Entity.Student;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "course")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String name;
@ManyToOne

private Student student;
}
