package me.paulau.mp.database.employee.resource;

import me.paulau.mp.database.employee.model.Employee;
import me.paulau.mp.database.employee.service.EmployeeServicePers;
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
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import static jakarta.ws.rs.core.Response.ok;

@Path("/employeeOld")
@RequestScoped
public class EmployeeResourceOld {

    private final EmployeeServicePers employeeServicePers;

    @PersistenceContext(unitName = "pu1")
    private EntityManager entityManager;

    @Inject
    public EmployeeResourceOld(EmployeeServicePers employeeServicePers) {
        this.employeeServicePers = employeeServicePers;
    }

    @GET
    @Path("/all")
    @APIResponse(responseCode = "200", description = "Find all employees",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Employee.class)))
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() {
        return ok(this.employeeServicePers.getAllEmployees()).build();
    }

    @POST
    @Path("/save")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Employee created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class))),
            @APIResponse(responseCode = "400", description = "Invalid form filling",
                    content = @Content)})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Employee employee) {
        Employee save = this.employeeServicePers.save(employee);
        return ok(save).build();
    }

    @DELETE
    @Path("/delete/{id}/")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Employee deleted",
                    content = @Content),
            @APIResponse(responseCode = "400", description = "Invalid form filling",
                    content = @Content)})
    public Response deleteOrderById(@PathParam("id") Long id) {
        try {
            this.employeeServicePers.deleteById(id);
        } catch (Exception e) {
            return Response.status(Response.Status.OK).entity("Delete failed").build();
        }
        return Response.status(Response.Status.OK).entity("Deleted successfully").build();
    }
}