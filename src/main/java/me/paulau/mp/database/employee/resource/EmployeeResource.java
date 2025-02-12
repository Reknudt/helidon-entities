package me.paulau.mp.database.employee.resource;

import me.paulau.mp.database.employee.model.Employee;
import me.paulau.mp.database.employee.service.EmployeeService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.Response.ok;

@Path("/employee")
@RequestScoped
public class EmployeeResource {

    private final EmployeeService employeeService;

    @PersistenceContext(unitName = "pu1")
    private EntityManager entityManager;

    @Inject
    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() {
        return ok(this.employeeService.getAllEmployees()).build();
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Employee employee) {
        return ok(this.employeeService.save(employee)).build();
    }

    @DELETE
    @Path("/delete/{id}/")
    public Response deleteOrderById(@PathParam("id") Long id) {
        try {
            this.employeeService.deleteById(id);
        } catch (Exception e) {
            return Response.status(Response.Status.OK).entity("Delete failed").build();
        }
        return Response.status(Response.Status.OK).entity("Deleted successfully").build();
    }
}