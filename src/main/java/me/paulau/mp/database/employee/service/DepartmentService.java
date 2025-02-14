package me.paulau.mp.database.employee.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import me.paulau.mp.database.employee.model.Department;
import me.paulau.mp.database.employee.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Inject
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public void update(Long id, Department departmentRequest) {
        Department department = findByIdOrThrow(id);

        department.setBossId(departmentRequest.getBossId());
        department.setEmployees(departmentRequest.getEmployees());
        department.setName(departmentRequest.getName());
        department.setProjects(departmentRequest.getProjects());

        departmentRepository.update(department);
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

    public Department findByIdOrThrow(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Department "+ id + " not found"));
    }
}