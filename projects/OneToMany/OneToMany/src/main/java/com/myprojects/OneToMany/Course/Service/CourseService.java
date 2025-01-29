package com.myprojects.OneToMany.Course.Service;

import com.myprojects.OneToMany.Course.Model.Course;
import com.myprojects.OneToMany.Course.Repository.CourseRepository;
import com.myprojects.OneToMany.Student.Model.Student;
import com.myprojects.OneToMany.Student.Repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<List<Course>> retrieveAllCourses(){
        try
        {
            List<Course> courses = courseRepository.findAll();
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Course> retrieveCourseById(int id1,int id){
        try
        {   Optional<Course> course = courseRepository.findById(id1);
            Optional<Student> student = studentRepository.findById(id);
            if(course.isEmpty() || student.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
                return new ResponseEntity<>(courseRepository.findById(id1).get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

public ResponseEntity<String> insertCourse(int id,Course course){
    try
    {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
           // course.setStudent(student.get());
            Course addedCourse = courseRepository.save(course);
            student.get().getCourses().add(addedCourse);
            studentRepository.save(student.get());
            return new ResponseEntity<>("Saved successfully", HttpStatus.OK);

        }

    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
public ResponseEntity<Course> updateCourse(int id,int id1,Course course){
    try
    {
        Optional<Student> student = studentRepository.findById(id);
        Optional<Course> getCourse = courseRepository.findById(id1);
        if(student.isEmpty() || getCourse.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            getCourse.get().setName(course.getName());
            courseRepository.save(getCourse.get());


            return new ResponseEntity<>(courseRepository.findById(id1).get(), HttpStatus.OK);

        }

    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
public ResponseEntity<String> deleteCourseById(int id,int id1){
    try
    {
        Optional<Student> student = studentRepository.findById(id);
        Optional<Course> getCourse = courseRepository.findById(id1);
        if(student.isEmpty() || getCourse.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            //getCourse.get().setStudent(null);
            student.get().getCourses().remove(getCourse.get());
            courseRepository.deleteById(id1);
            return new ResponseEntity<>("course deleted", HttpStatus.OK);

        }

    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}

