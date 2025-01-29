package com.myprojects.ManyToMany.Student.Controller;

import com.myprojects.ManyToMany.Student.Model.Student;
import com.myprojects.ManyToMany.Student.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public ResponseEntity<String> insertStudent(@RequestBody Student student){
        return studentService.insertStudent(student);
    }
    @GetMapping("/students")
    public ResponseEntity<List<Student>> retrieveAllStudents(){
        return studentService.retrieveAllStudents();
    }
    @PostMapping("students/{sid}/assign-courses/{cid}")
    public ResponseEntity<Student> assignCourseaToAStudent(@PathVariable int sid,@PathVariable int cid){
        return studentService.assignCourseToAStudent(sid,cid);
    }
    @PutMapping("/students/{sid}")
    public ResponseEntity<Student> updateStudentName(@PathVariable int sid,@RequestBody Student student){
        return studentService.updateStudentName(sid,student);
    }
    @DeleteMapping("/students/{sid}")
    public ResponseEntity<String> deleteStudent(@PathVariable int sid){
        return studentService.deleteStudent(sid);
    }
}
