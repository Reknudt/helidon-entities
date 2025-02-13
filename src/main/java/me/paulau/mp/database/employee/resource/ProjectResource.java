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
import me.paulau.mp.database.employee.model.Department;
import me.paulau.mp.database.employee.model.Project;
import me.paulau.mp.database.employee.service.ProjectService;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.util.List;

import static jakarta.ws.rs.core.Response.ok;

@Path("/project")
@ApplicationScoped
public class ProjectResource {

    private final ProjectService projectService;

    @Inject
    public ProjectResource(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GET
    @Path("/all")
    @APIResponse(responseCode = "200", description = "Find all projects",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Project.class)))
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @POST
    @Path("/save")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Project created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Project.class))),
            @APIResponse(responseCode = "400", description = "Invalid form filling",
                    content = @Content)})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Project project) {
        Project save = this.projectService.create(project);
        return ok(save).build();
    }

    @PUT
    @Path("/{id}")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Project updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Department.class))),
            @APIResponse(responseCode = "400", description = "Invalid form filling",
                    content = @Content)})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") Long id, @RequestBody Project projectRequest) {
        projectService.update(id, projectRequest);
    }

    @DELETE
    @Path("/delete/{id}/")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Project deleted",
                    content = @Content),
            @APIResponse(responseCode = "400", description = "Invalid form filling",
                    content = @Content)})
    public Response deleteProjectById(@PathParam("id") Long id) {
        try {
            this.projectService.deleteById(id);
        } catch (Exception e) {
            return Response.status(Response.Status.OK).entity("Delete failed").build();
        }
        return Response.status(Response.Status.OK).entity("Deleted successfully").build();
    }
}