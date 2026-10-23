
package com.example.employeemanager.service;

import com.example.employeemanager.model.Employee;
import com.example.employeemanager.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
            .orElseThrow(() ->
                new IllegalArgumentException(
                    "Employee not found: " + id));
    }

    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    public boolean emailExists(String email) {
        return repository.existsByEmail(email);
    }

    public boolean emailUsedByAnother(
            String email, Long employeeId) {
        return repository.findByEmail(email)
            .map(e -> !e.getId().equals(employeeId))
            .orElse(false);
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
