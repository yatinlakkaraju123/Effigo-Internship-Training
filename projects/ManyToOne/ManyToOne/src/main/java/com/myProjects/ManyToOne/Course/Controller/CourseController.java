package com.myProjects.ManyToOne.Course.Controller;

import com.myProjects.ManyToOne.Course.Entity.Course;
import com.myProjects.ManyToOne.Course.Service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> retrieveAllCourses(){
        return courseService.retrieveAllCourses();
    }
    @GetMapping("students/{id}/courses")
    public ResponseEntity<List<Course>> retrieveAllCoursesByUser(@PathVariable int id){
        return courseService.retrieveAllCoursesByUser(id);
    }
    @GetMapping("students/{id}/courses/{id1}")
    public ResponseEntity<Course> retrieveCourseById(@PathVariable int id1,@PathVariable int id){
        return courseService.retrieveCourseById(id1,id);
    }
    @PostMapping("students/{id}/courses")
    public ResponseEntity<String> insertCourse(@PathVariable int id, @RequestBody Course course)
    {
        return courseService.insertCourse(id,course);
    }
    @PutMapping("students/{id}/courses/{id1}")
    public ResponseEntity<Course> updateCourseById(@PathVariable int id,@PathVariable int id1,@RequestBody Course course){
        return courseService.updateCourse(id, id1, course);
    }
    @DeleteMapping("students/{id}/courses/{id1}")
    public ResponseEntity<String> deleteCourseById(@PathVariable int id,@PathVariable int id1){
            return courseService.deleteCourseById(id,id1);
    }
}
