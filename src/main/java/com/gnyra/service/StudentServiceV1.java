package com.gnyra.service;

import com.gnyra.dto.Student;
import com.gnyra.repository.StudentHashMapDB;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StudentServiceV1 {

    @Autowired
    private StudentHashMapDB studentHashMapDB;

    public ArrayList<Student> giveAllStudents() {
        return new ArrayList<>(studentHashMapDB.studentInfoDb.values());
    }

    public ResponseEntity<Student> getMyStudentById(Integer id) {
        Student student = studentHashMapDB.studentInfoDb.get(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    public ResponseEntity<Student> addNewStudent(@Valid Student student) {
        log.info("Adding new student: {}", student);
        return studentHashMapDB.storeStudentInfo(student);
    }

    public ResponseEntity<Student> updateStudentInfo(Integer id, @Valid Student student) {
        return studentHashMapDB.updateStudentInDb(id, student);
    }

    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(new ArrayList<>(studentHashMapDB.studentInfoDb.values()));
    }
}
