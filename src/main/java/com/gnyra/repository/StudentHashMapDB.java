package com.gnyra.repository;

import com.gnyra.dto.Student;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class StudentHashMapDB {
    private final AtomicInteger idGenerator = new AtomicInteger();
    public final Map<Integer, Student> studentInfoDb = Collections.synchronizedMap(new LinkedHashMap<>());

    public ResponseEntity<Student> storeStudentInfo(@Valid Student student) {
        int id = idGenerator.incrementAndGet();
        student.setId(id);
        studentInfoDb.put(id, student);
        log.info("Stored student in DB: {}", student);
        return ResponseEntity.status(201).body(student);
    }

    public ResponseEntity<Student> updateStudentInDb(Integer id, @Valid Student student) {
        if (!studentInfoDb.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        Student existingStudent = studentInfoDb.get(id);
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        studentInfoDb.put(id, existingStudent);

        return ResponseEntity.ok(existingStudent);
    }
}
