package me.paulau.mp.database.employee.service;

import me.paulau.mp.database.employee.model.Employee;
import me.paulau.mp.database.employee.repository.EmployeeRepositoryPersist;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EmployeeServicePers {

    private final EmployeeRepositoryPersist employeeRepositoryPersist;

    @Inject
    public EmployeeServicePers(EmployeeRepositoryPersist employeeRepositoryPersist) {
        this.employeeRepositoryPersist = employeeRepositoryPersist;
    }

    @Transactional
    public Employee save(Employee employee) {
        return employeeRepositoryPersist.createOrUpdate(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepositoryPersist.getAllEmployees();
    }

    @Transactional
    public void deleteById(Long id) {
        employeeRepositoryPersist.deleteById(id);
    }
}