package me.paulau.mp.database.employee.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.Getter;
import me.paulau.mp.database.employee.model.Department;
import me.paulau.mp.database.employee.service.DepartmentService;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;


import static jakarta.ws.rs.core.Response.ok;

@Path("/department")
@ApplicationScoped
@Getter
public class DepartmentResource {

    private final DepartmentService departmentService;

    @Inject
    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GET
    @Path("/all")
    @APIResponse(responseCode = "200", description = "Find all departments", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Department.class)))
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartments() {
        return ok(departmentService.getAllDepartments()).build();
    }

    @POST
    @Path("/save")
    @APIResponse(responseCode = "201", description = "Department created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Department.class)))
    @APIResponse(responseCode = "400", description = "Invalid form filling", content = @Content)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Department department) {
        Department save = this.departmentService.create(department);
        return ok(save).build();
    }

    @PUT
    @Path("/{id}")
    @APIResponse(responseCode = "200", description = "Department updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Department.class)))
    @APIResponse(responseCode = "400", description = "Invalid form filling", content = @Content)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") Long id, @RequestBody Department departmentRequest) {
        departmentService.update(id, departmentRequest);
    }

    @DELETE
    @Path("/delete/{id}/")
    @APIResponse(responseCode = "204", description = "Department deleted", content = @Content)
    @APIResponse(responseCode = "400", description = "Invalid form filling", content = @Content)
    public Response deleteDepartmentById(@PathParam("id") Long id) {
        try {
            this.departmentService.deleteById(id);
        } catch (Exception e) {
            return Response.status(Response.Status.OK).entity("Delete failed").build();
        }
        return Response.status(Response.Status.OK).entity("Deleted successfully").build();
    }
}
