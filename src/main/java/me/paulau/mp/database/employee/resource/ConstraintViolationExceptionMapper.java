package me.paulau.mp.database.employee.resource;

import java.net.URI;
import java.util.stream.Collectors;

import io.github.belgif.rest.problem.api.Problem;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import io.github.belgif.rest.problem.BadRequestProblem;
import io.github.belgif.rest.problem.api.InputValidationIssue;


@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Context
    UriInfo uriInfo;
//    private static final DefaultExceptionMapper DEFAULT_MAPPER = new DefaultExceptionMapper();

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        try {
            BadRequestProblem problem = new BadRequestProblem(
                    exception.getConstraintViolations().stream()
                            .map(ConstraintViolationUtil::convertToInputValidationIssue)
                            .sorted(InputValidationIssue.BY_NAME)
                            .collect(Collectors.toList()));
            problem.setInstance(uriInfo.getRequestUri());
//            problem.setAdditionalProperty("", exception.);
            problem.setHref(URI.create(uriInfo.getPath()));
            return ProblemMediaType.INSTANCE.toResponse(problem);
        } catch (RuntimeException e) {
            return ProblemMediaType.INSTANCE.toResponse((Problem) e);
        }
    }

}