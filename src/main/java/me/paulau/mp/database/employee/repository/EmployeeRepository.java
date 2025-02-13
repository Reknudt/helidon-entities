package me.paulau.mp.database.employee.repository;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Repository;
import me.paulau.mp.database.employee.model.Employee;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

//    @Delete
//    void deleteById(long id);
}