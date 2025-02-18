package me.paulau.mp.database.employee.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import me.paulau.mp.database.employee.model.Department;
import me.paulau.mp.database.employee.model.Project;
import me.paulau.mp.database.employee.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final DepartmentService departmentService;

    @Inject
    public ProjectService(ProjectRepository projectRepository, DepartmentService departmentService) {
        this.projectRepository = projectRepository;
        this.departmentService = departmentService;
    }

    @Transactional
    public Project create(Project project) {
        return projectRepository.insert(project);
    }

    @Transactional
    public void update(Long id, Project projectRequest) {
        Project project = findByIdOrThrow(id);

        project.setName(projectRequest.getName());

        if (projectRequest.getDepartments() != null) {
            project.getDepartments().clear();
            project.getDepartments().addAll(projectRequest.getDepartments());
        } else {
            project.getDepartments().clear();
        }

        projectRepository.save(project);
    }


    public List<Project> getAllProjects() {
        return projectRepository.findAll().toList();
    }

    @Transactional
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

    @Transactional
    public void assignDepartment(Long id, Long departmentId) {
        Project project = findByIdOrThrow(id);
        Department newDepartment = departmentService.findByIdOrThrow(departmentId);

        List<Department> departmentList = project.getDepartments();
        departmentList.add(newDepartment);
        project.setDepartments(departmentList);
        projectRepository.save(project);
    }

    @Transactional
    public void removeDepartment(Long id, Long departmentId) {
        Project project = findByIdOrThrow(id);
        Department newDepartment = departmentService.findByIdOrThrow(departmentId);

        List<Department> departmentList = project.getDepartments();
        departmentList.add(newDepartment);
        project.setDepartments(departmentList);
        projectRepository.save(project);
    }

    //department_projects ids swap
    @Transactional///$ curl -X PUT http://localhost:8080/project/assignDepartmentList/3 -H "Content-Type: application/json" -d '[1, 2, 3]'
    public void updateDepartmentList(Long id, List<Long> departmentIds) {
        Project project = findByIdOrThrow(id);
        List<Department> newDepartments = new ArrayList<>(List.of());

        for (Long departmentId : departmentIds) {
            Department department = departmentService.findByIdOrThrow(departmentId);
            newDepartments.add(department);
        }

        project.setDepartments(newDepartments);
        projectRepository.save(project);
    }

    private Project findByIdOrThrow(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Project " + id + " not found"));
    }
}