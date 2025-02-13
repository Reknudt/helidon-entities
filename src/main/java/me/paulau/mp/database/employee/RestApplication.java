package me.paulau.mp.database.employee;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import me.paulau.mp.database.employee.resource.EmployeeResource;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
@ApplicationPath("/rest")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(EmployeeResource.class);
        return classes;
    }
}
