package me.paulau.mp.database.employee.repository;

import me.paulau.mp.database.employee.model.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EmployeeRepositoryPersist {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public Employee createOrUpdate(Employee employee) {
        if (employee == null) {
            this.entityManager.persist(employee);
            return employee;
        } else {
            return this.entityManager.merge(employee);
        }
    }

    @Transactional
    public void deleteById(Long id) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaDelete<Employee> delete = cb.createCriteriaDelete(Employee.class);
        Root<Employee> root = delete.from(Employee.class);
        delete.where(cb.equal(root.get("id"), id));
        this.entityManager.createQuery(delete).executeUpdate();
    }

    public List<Employee> getAllEmployees() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> rootEntry = cq.from(Employee.class);
        CriteriaQuery<Employee> all = cq.select(rootEntry);
        TypedQuery<Employee> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }
}