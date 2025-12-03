package com.gnyra.service;

import com.gnyra.dto.Student;
import com.gnyra.repository.StudentDB;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentDB studentDB;

    public ArrayList<Student> giveAllStudents() {
        return new ArrayList<>(studentDB.studentInfoDb.values());
    }

    public ResponseEntity<Student> getMyStudentById(Integer id) {
        Student student = studentDB.studentInfoDb.get(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    public ResponseEntity<Student> addNewStudent(@Valid Student student) {
        log.info("Adding new student: {}", student);
        return studentDB.storeStudentInfo(student);
    }

    public ResponseEntity<Student> updateStudentInfo(Integer id, @Valid Student student) {
        return studentDB.updateStudentInDb(id, student);
    }

    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(new ArrayList<>(studentDB.studentInfoDb.values()));
    }
}
