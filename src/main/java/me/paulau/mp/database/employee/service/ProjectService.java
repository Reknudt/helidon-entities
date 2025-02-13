package me.paulau.mp.database.employee.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

    public List<Project> getAllProjects() {
        return projectRepository.findAll().toList();
    }

    @Transactional
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}