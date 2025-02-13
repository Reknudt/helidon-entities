package me.paulau.mp.database.employee.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import me.paulau.mp.database.employee.model.Department;
import me.paulau.mp.database.employee.model.Employee;
import me.paulau.mp.database.employee.model.Project;
import me.paulau.mp.database.employee.repository.ProjectRepository;

import java.util.List;

@ApplicationScoped
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Inject
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
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

    private Project findByIdOrThrow(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Project " + id + " not found"));
    }
}