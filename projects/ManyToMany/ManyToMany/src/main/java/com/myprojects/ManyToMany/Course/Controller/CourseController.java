package com.myprojects.ManyToMany.Course.Controller;

import com.myprojects.ManyToMany.Course.Model.Course;
import com.myprojects.ManyToMany.Course.Service.CourseService;
import com.myprojects.ManyToMany.Student.Model.Student;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @PostMapping("/courses")
    public ResponseEntity<String> insertCourse(@RequestBody Course course){
        return courseService.insertCourse(course);
    }
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> retrieveAllCourses(){
        return courseService.retrieveAllCourses();
    }
    @PutMapping(value = "/courses/{cid}")
    public ResponseEntity<Course> updateCourseName(@PathVariable int cid, @RequestBody Course course) {
        return courseService.updateCourseName(cid, course);
    }
    @DeleteMapping("/courses/{cid}")
    public ResponseEntity<String> deleteCourse(@PathVariable int cid){
        return courseService.deleteCourse(cid);
    }


}
