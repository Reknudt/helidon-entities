package me.paulau.mp.database.employee.repository;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;
import me.paulau.mp.database.employee.model.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
