package com.myprojects.OneToMany.Student.Model;

import com.myprojects.OneToMany.Course.Model.Course;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany
    private List<Course> courses;
}
