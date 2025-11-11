package com.example.studentdetails.controller;

import com.example.studentdetails.dto.Student;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/students")  // Changed to match requirement /students
public class StudentController {
    private final AtomicInteger idGenerator = new AtomicInteger();
    private final Map<Integer, Student> store = Collections.synchronizedMap(new LinkedHashMap<>());

    /**
     * GET /students - Return all students
     */
    @GetMapping
    public List<Student> listStudents() {
        return new ArrayList<>(store.values());
    }

    /**
     * GET /students/{id} - Return a single student by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        Student student = store.get(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    /**
     * POST /students - Add a new student
     * Example request body:
     * { "name": "Alice Smith", "email": "alice@example.com" }
     */
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        int id = idGenerator.incrementAndGet();
        student.setId(id);
        store.put(id, student);
        return ResponseEntity.status(201).body(student);
    }

    /**
     * PUT /students/{id} - Update student's name & email
     * Example request body:
     * { "name": "Alice Smith", "email": "alicesmith@example.com" }
     */
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @Valid @RequestBody Student student) {
        if (!store.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        Student existingStudent = store.get(id);
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        store.put(id, existingStudent);

        return ResponseEntity.ok(existingStudent);
    }

    /**
     * GET /students/all - Return all students (alias for /students)
     */
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(new ArrayList<>(store.values()));
    }
}