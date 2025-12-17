package com.gnyra.controller;

import com.gnyra.dto.Student;
import com.gnyra.service.StudentServiceV2;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/students/v2")
@Slf4j
public class StudentControllerV2 {
    @Autowired
    private StudentServiceV2 studentService;

    /**
     * GET /students/v2 - Return all students
     */
    @GetMapping
    public List<Student> listStudents() {
        return studentService.giveAllStudents();
    }

    /**
     * GET /students/v2/{id} - Return a single student by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        return studentService.getMyStudentById(id);
    }

    /**
     * POST /students/v2 - Add a new student
     * Example request body:
     * { "name": "Alice Smith", "email": "alice@example.com" }
     */
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        log.info("Received request to create student: {}", student);
        return studentService.addNewStudent(student);
    }

    /**
     * PUT /students/v2/{id} - Update student's name & email
     * Example request body:
     * { "name": "Alice Smith", "email": "alicesmith@example.com" }
     */
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @Valid @RequestBody Student student) {
        return studentService.updateStudentInfo(id, student);
    }

    /**
     * GET /students/v2/all - Return all students (alias for /students/v2)
     */
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        return studentService.getAllStudents();
    }
}
