package com.myprojects.ManyToMany.Student.Service;

import com.myprojects.ManyToMany.Course.Model.Course;
import com.myprojects.ManyToMany.Course.Repository.CourseRepository;
import com.myprojects.ManyToMany.Student.Model.Student;
import com.myprojects.ManyToMany.Student.Repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

    public StudentService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<String> insertStudent(Student student){
        try
        {
            Student addedStudent = studentRepository.save(student);
            return new ResponseEntity<>("inserted student",HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<Student>> retrieveAllStudents(){
        try {   List<Student> students = studentRepository.findAll();
            return new ResponseEntity<>(students,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Student> assignCourseToAStudent(int sid,int cid){
        try
        {
            Optional<Course> getCourse = courseRepository.findById(cid);
            Optional<Student> student = studentRepository.findById(sid);
            List<Course> coursesToAssign = null;
            if(getCourse.isEmpty() ||  student.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                coursesToAssign = student.get().getCourses();
                coursesToAssign.add(getCourse.get());
                student.get().setCourses(coursesToAssign);
                studentRepository.save(student.get());
                return new ResponseEntity<>(studentRepository.findById(sid).get(),HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<Student> updateStudentName(int cid,Student student){
        try
        {
            Optional<Student> getStudent = studentRepository.findById(cid);
            if(getStudent.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                getStudent.get().setName(student.getName());
                studentRepository.save(getStudent.get());
                return new ResponseEntity<>(studentRepository.findById(cid).get(),HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<String> deleteStudent(int sid){
        try
        {
            Optional<Student> getStudent = studentRepository.findById(sid);
            if(getStudent.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                for(Course course:getStudent.get().getCourses())
                {
                        course.getStudents().remove(getStudent.get());
                }
                courseRepository.saveAll(getStudent.get().getCourses());
                studentRepository.delete(getStudent.get());
                return new ResponseEntity<>("deleted",HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
