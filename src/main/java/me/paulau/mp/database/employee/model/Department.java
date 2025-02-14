package me.paulau.mp.database.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity(name = "department")
@Table(name = "Department")
public class Department {

    @Setter
    private long id;

    @Setter
    private String name;

    @Setter
    private long bossId;

    private List<Employee> employees = new ArrayList<>();

    private List<Project> projects = new ArrayList<>();

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    public long getId() {
        return id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "boss_id")
    public long getBossId() {
        return bossId;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees != null ? employees : new ArrayList<>();
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "departments", fetch = FetchType.EAGER)
    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects != null ? projects : new ArrayList<>();
    }
}
