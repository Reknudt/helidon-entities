package me.paulau.mp.database.employee.repository;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;
import me.paulau.mp.database.employee.model.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
