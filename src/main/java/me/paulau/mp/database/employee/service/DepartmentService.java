package me.paulau.mp.database.employee.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import me.paulau.mp.database.employee.model.Department;
import me.paulau.mp.database.employee.repository.DepartmentRepository;

import java.util.List;

@ApplicationScoped
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Inject
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public Department create(Department department) {
        return departmentRepository.insert(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll().toList();
    }

    @Transactional
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }
}