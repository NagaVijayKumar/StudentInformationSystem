package com.test.information.controller;

import com.test.information.model.Student;
import com.test.information.repository.StudentRepository;
import com.test.information.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class StudentController {

    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/api/students/add")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        if (studentRepository.findByRollNumber(student.getRollNumber()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.CREATED);
    }

    @PostMapping("/api/students/update")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        if (!studentRepository.findByRollNumber(student.getRollNumber()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.CREATED);
    }

    @GetMapping("/api/students/all")
    public ResponseEntity<?> allStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }



    @DeleteMapping("/api/students/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Long id) throws Exception {
        studentRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
