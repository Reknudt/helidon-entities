package me.paulau.mp.database.employee.repository;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Find;
import jakarta.data.repository.Insert;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Update;
import me.paulau.mp.database.employee.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Find
    List<Employee> findAllEmployees();

    @Insert
    Employee insert(Employee employee);

    @Update
    Employee update(Employee employee);

    @Delete
    void deleteById(long id);
}