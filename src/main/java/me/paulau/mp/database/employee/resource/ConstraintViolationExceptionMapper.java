package me.paulau.mp.database.employee.resource;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;


@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(final ConstraintViolationException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getLocalizedMessage()).build();
    }

//    @Override
//    public Response toResponse(final ConstraintViolationException exception) {
//        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
//        final var jsonObject = Json.createObjectBuilder()
//                .add("type", "about:blank")
//                .add("instance", uriInfo.getAbsolutePath().getPath())
//                .add("title", "Bad Request")
//                .add("status", "400");
//        final var jsonArray = Json.createArrayBuilder();
//
//        for (final var constraint : constraintViolations) {
//            String className = constraint.getLeafBean().toString();
//            String template = constraint.getMessageTemplate();
//            ResourceBundle bundle = ResourceBundle.getBundle("org.hibernate.validator.ValidationMessages", Locale.ENGLISH);
//            String localizedMessage = bundle.getString(template.replaceAll("[{}]",""));
//            String propertyPath = constraint.getPropertyPath().toString();
//
//            JsonObject jsonError = Json.createObjectBuilder()
//                    .add("class", className.split("@")[0])
//                    .add("field", propertyPath)
//                    .add("violationMessage", localizedMessage)
//                    .build();
//            jsonArray.add(jsonError);
//        }
//        JsonObject errorJsonEntity = jsonObject.add("details", jsonArray.build()).build();
//        return Response.status(Response.Status.BAD_REQUEST).entity(errorJsonEntity).build();
//    }
}
