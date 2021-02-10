package com.github.demo.controller;

import com.github.demo.exceptions.EmployeeNotFoundException;
import com.github.demo.model.Employee;
import com.github.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository repository;

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee emp = repository.save(employee);

        return ResponseEntity.status(HttpStatus.OK).body(emp);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        Employee emp = repository.findById(id).map(e -> {
            e.setName(employee.getName());
            e.setSalary(employee.getSalary());
            e.setAge(employee.getAge());
            return repository.save(e);
        }).orElseThrow(() -> new EmployeeNotFoundException(id));

        return ResponseEntity.status(HttpStatus.CREATED).body(emp);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
