package me.paulau.mp.database.employee.resource;
//
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.ConstraintViolationException;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import jakarta.ws.rs.ext.ExceptionMapper;
//import jakarta.ws.rs.ext.Provider;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
//@Provider
//public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
//
//    @Override
//    public Response toResponse(ConstraintViolationException exception) {
//        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
//        Map<String, String> errors = new HashMap<>();
//
//        for (ConstraintViolation<?> violation : violations) {
//            String property = violation.getPropertyPath().toString();
//            String message = violation.getMessage();
//            errors.put(property, message);
//        }
//
//        return Response.status(Response.Status.BAD_REQUEST)
//            .type(MediaType.APPLICATION_JSON)
//            .entity(errors)
//            .build();
//    }
//}
