package com.gnyra.service;

import com.gnyra.dto.Student;
import com.gnyra.entity.StudentEntity;
import com.gnyra.repository.StudentJpaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceV2 {

    private final StudentJpaRepository repository;

    public List<Student> giveAllStudents() {
        return repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<Student> getMyStudentById(Integer id) {
        Optional<StudentEntity> student = repository.findById(id.longValue());
        return student.map(entity -> ResponseEntity.ok(toDto(entity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Student> addNewStudent(@Valid Student student) {
        log.info("Adding new student to H2: {}", student);
        StudentEntity entity = toEntity(student);
        entity.setId(null);
        StudentEntity saved = repository.save(entity);
        return ResponseEntity.status(201).body(toDto(saved));
    }

    public ResponseEntity<Student> updateStudentInfo(Integer id, @Valid Student student) {
        return repository.findById(id.longValue())
                .map(existing -> {
                    existing.setName(student.getName());
                    existing.setEmail(student.getEmail());
                    StudentEntity updated = repository.save(existing);
                    return ResponseEntity.ok(toDto(updated));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(giveAllStudents());
    }

    private Student toDto(StudentEntity entity) {
        Student dto = new Student();
        dto.setId(entity.getId().intValue());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    private StudentEntity toEntity(Student student) {
        StudentEntity entity = new StudentEntity();
        if (student.getId() != null) {
            entity.setId(student.getId().longValue());
        }
        entity.setName(student.getName());
        entity.setEmail(student.getEmail());
        return entity;
    }
}

