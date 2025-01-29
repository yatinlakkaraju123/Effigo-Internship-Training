package com.myprojects.OneToMany.Student.Controller;

import com.myprojects.OneToMany.Student.Model.Student;
import com.myprojects.OneToMany.Student.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/students")
    public ResponseEntity<List<Student>> retrieveAllStudents(){
        return studentService.retrieveAllUsers();
    }
    @GetMapping("students/{id}")
    public ResponseEntity<Student> retrieveStudentById(@PathVariable int id){
        return studentService.retrieveStudentById(id);
    }

    @PostMapping("/students")
    public ResponseEntity<String> insertStudent(@RequestBody Student student){
        return studentService.insertStudent(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable int id){
        return studentService.updateStudent(id,student);
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id){
        return studentService.deleteStudent(id);
    }
}
