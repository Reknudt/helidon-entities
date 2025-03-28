package me.paulau.mp.database.employee.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.eclipse.microprofile.openapi.annotations.media.Schema;



@Entity(name = "employee")
@Table(name = "Employee")
@Schema(description = "Employee model")
public class Employee {

    public Employee() {
    }

    @Schema(description = "Unique employee id")
    private long id;

    @Schema(description = "Employee name")
    @NotBlank
    private String name;

    @Schema(description = "Id of employee's boss")
    @PositiveOrZero
    private long bossId;

    @Schema(description = "Id of employee's department")
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
