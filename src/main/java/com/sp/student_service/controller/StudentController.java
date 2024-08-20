package com.sp.student_service.controller;

import com.sp.student_service.dto.ResponseDTO;
import com.sp.student_service.model.Student;
import com.sp.student_service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api2/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping
    public Student addStudent(@RequestBody Student student)
    {
        return studentService.addStudent(student);
    }
    @GetMapping("/{rollNo}")
    public ResponseDTO getStudent(@PathVariable int rollNo)
    {
        return studentService.getStudent(rollNo);
    }
    @GetMapping
    public List<Student> getAllStudent()
    {
        return studentService.getAllStudent();
    }
    @PutMapping("/{rollNo}")
    public Student updateStudent(@PathVariable int rollNo,@RequestBody Student student)
    {
        return studentService.updateStudent(rollNo,student);
    }
    @DeleteMapping("/{rollNo}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable int rollNo)
    {
        return studentService.deleteStudent(rollNo);
    }
}
