package com.myprojects.OneToMany.Student.Service;

import com.myprojects.OneToMany.Student.Model.Student;
import com.myprojects.OneToMany.Student.Repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public ResponseEntity<List<Student>> retrieveAllUsers(){
        try
        {
            List<Student> students = studentRepository.findAll();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Student> retrieveStudentById(int id){
        try
        {
            Optional<Student> student= studentRepository.findById(id);
            if(student.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                return new ResponseEntity<>(student.get(),HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> insertStudent(Student student){
        try
        {
            studentRepository.save(student);
            return new ResponseEntity<>("student created",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    public ResponseEntity<Student> updateStudent(int id,Student student){
        try
        {   Optional<Student> getStudent = studentRepository.findById(id);
            if(getStudent.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                getStudent.get().setName(student.getName());
                studentRepository.save(getStudent.get());
                return new ResponseEntity<>(studentRepository.findById(id).get(),HttpStatus.OK);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<String> deleteStudent(int id){
        try
        {   Optional<Student> getStudent = studentRepository.findById(id);
            if(getStudent.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            {
                studentRepository.delete(getStudent.get());
                return new ResponseEntity<>("student deleted",HttpStatus.OK);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
