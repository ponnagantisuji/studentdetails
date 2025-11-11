package com.example.studentdetails.controller;

import com.example.studentdetails.dto.Employee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final AtomicInteger idGenerator = new AtomicInteger();
    private final Map<Integer, Employee> store = Collections.synchronizedMap(new LinkedHashMap<>());

    /**
     * GET /employee - Return all employees
     */
    @GetMapping
    public List<Employee> listEmployees() {
        return new ArrayList<>(store.values());
    }

    /**
     * POST /employee - Add a new employee
     * Example request body:
     * { "name": "John Doe", "email": "john@example.com", "salary": 50000 }
     */
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        int id = idGenerator.incrementAndGet();
        employee.setId(id);
        store.put(id, employee);
        return ResponseEntity.status(201).body(employee);
    }

    /**
     * PUT /employee/{id} - Update employee's name & email
     * Example request body:
     * { "name": "John Doe", "email": "johndoe@example.com", "salary": 55000 }
     */
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @Valid @RequestBody Employee employee) {
        if (!store.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        Employee existingEmployee = store.get(id);
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setSalary(employee.getSalary());
        store.put(id, existingEmployee);

        return ResponseEntity.ok(existingEmployee);
    }
}
