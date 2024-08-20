package com.sp.student_service.service;

import com.sp.student_service.dao.StudentRepository;
import com.sp.student_service.dto.CollegeDTO;
import com.sp.student_service.dto.ResponseDTO;
import com.sp.student_service.dto.StudentDTO;
import com.sp.student_service.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private WebClient webClient;

    public Student addStudent(Student student)
    {
        return studentRepository.save(student);
    }
    public List<Student> getAllStudent()
    {
        return studentRepository.findAll();
    }
    public ResponseDTO getStudent(int rollNo)
    {
        Student student= studentRepository.findById(rollNo).orElseThrow(()->new RuntimeException("student does not available.."));
        StudentDTO studentDTO=mapTOStudent(student);
        CollegeDTO collegeDTO=webClient.get().uri("/api1/college/"+student.getClgID()).retrieve().bodyToMono(CollegeDTO.class).block();
        return new ResponseDTO(studentDTO,collegeDTO);

    }
    public Student updateStudent(int rollNo,Student student)
    {
        Student updateStudent=studentRepository.findById(rollNo).orElseThrow(()->new RuntimeException("student is not available to update it.."));
        updateStudent.setStudName(student.getStudName());
        updateStudent.setEmail(student.getEmail());
        updateStudent.setPhone(student.getPhone());
        updateStudent.setClgID(student.getClgID());
        return studentRepository.save(updateStudent);
    }
    public ResponseEntity<HttpStatus> deleteStudent(int rollNo) {
        Student deleteStudent = studentRepository.findById(rollNo).orElseThrow(() -> new RuntimeException(("student not available to delete..")));
        studentRepository.deleteById(rollNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    public StudentDTO mapTOStudent(Student student)
    {
        return new StudentDTO(student.getRollNoll(),student.getStudName(),student.getEmail(),student.getPhone(),student.getClgID());
    }

}
