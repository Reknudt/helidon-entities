package me.paulau.mp.database.employee.resource;

import java.util.Map;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import io.github.belgif.rest.problem.api.HttpResponseHeaders;
import io.github.belgif.rest.problem.api.Problem;


public class ProblemMediaType extends MediaType {

    public static final ProblemMediaType INSTANCE = new ProblemMediaType();

    private ProblemMediaType() {
        super("application", "problem+json");
    }

    public Response toResponse(Problem problem) {
        Response.ResponseBuilder responseBuilder =
                Response.status(problem.getStatus()).type(ProblemMediaType.INSTANCE).entity(problem);
        if (problem instanceof HttpResponseHeaders) {
            Map<String, Object> headers = ((HttpResponseHeaders) problem).getHttpResponseHeaders();
            headers.forEach(responseBuilder::header);
        }
        return responseBuilder.build();
    }

}