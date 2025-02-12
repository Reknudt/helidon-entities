package me.paulau.mp.database.employee.service;

import me.paulau.mp.database.employee.model.Employee;
import me.paulau.mp.database.employee.repository.EmployeeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Inject
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.createOrUpdate(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Transactional
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}