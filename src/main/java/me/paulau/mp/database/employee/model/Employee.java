package me.paulau.mp.database.employee.model;


import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@NamedQueries({
//        @NamedQuery(name = "getEmployees",
//                query = "SELECT e FROM Employee e"),
//        @NamedQuery(name = "getEmployeeById",
//                query = "SELECT e FROM Employee e WHERE e.id = :id")
//})
//@Getter
//@Setter
//@NoArgsConstructor
@Entity(name = "employee")
@Table(name = "employee")
public class Employee {

    private long id;

    private String name;

    private long bossId;

    private long departmentId;

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "boss_id")
    public long getBossId() {
        return bossId;
    }

    public void setBossId(long bossId) {
        this.bossId = bossId;
    }

    @Column(name = "department_id")
    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }
}
