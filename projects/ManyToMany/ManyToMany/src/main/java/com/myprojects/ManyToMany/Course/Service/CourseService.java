package com.myprojects.ManyToMany.Course.Service;

import com.myprojects.ManyToMany.Course.Model.Course;
import com.myprojects.ManyToMany.Course.Repository.CourseRepository;
import com.myprojects.ManyToMany.Student.Model.Student;
import com.myprojects.ManyToMany.Student.Repository.StudentRepository;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class CourseService {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(CourseService.class);
    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<String> insertCourse(Course course){
        try {
            Course savedCourse = courseRepository.save(course);
            return new ResponseEntity<>("saved course",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<Course>> retrieveAllCourses(){
        try {
            return new ResponseEntity<>(courseRepository.findAll(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Course> updateCourseName(int cid,Course course){
        try
        {       //logger.info("the recieved course:"+course.toString());
            Optional<Course> getCourse = courseRepository.findById(cid);
            if(getCourse.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                getCourse.get().setName(course.getName());
                courseRepository.save(getCourse.get());
                return new ResponseEntity<>(courseRepository.findById(cid).get(),HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<String> deleteCourse(int cid){
        try
        {
            Optional<Course> getCourse = courseRepository.findById(cid);
            if(getCourse.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                for(Student student: getCourse.get().getStudents())
                {
                    student.getCourses().remove(getCourse.get());
                }
                studentRepository.saveAll(getCourse.get().getStudents());
                courseRepository.delete(getCourse.get());
                return new ResponseEntity<>("deleted course",HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
