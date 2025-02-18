package me.paulau.mp.database.employee.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "project")
@Table(name = "project")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Project {

    private long id;

    private String name;

    private List<Department> departments = new ArrayList<>();

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    public long getId() {
        return id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "department_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id"))
    public List<Department> getDepartments() {
        return departments;
    }

}
