//package me.paulau.mp.database.employee.resource;
//
//import com.github.t1.problemdetail.Constants;
//import com.github.t1.problemdetail.ProblemDetail;
//import com.github.t1.problemdetail.ri.lib.ProblemDetails;
//import jakarta.json.Json;
//import jakarta.json.JsonObject;
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.ConstraintViolationException;
//import jakarta.ws.rs.core.Context;
//import jakarta.ws.rs.core.HttpHeaders;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import jakarta.ws.rs.core.UriInfo;
//import jakarta.ws.rs.ext.ExceptionMapper;
//import jakarta.ws.rs.ext.Provider;
//import lombok.extern.slf4j.Slf4j;
//
//import java.net.URI;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
//@Slf4j
//@Provider
//public class ProblemDetailExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
//
//    @Context
//    UriInfo uriInfo;
//    @Context
//    HttpHeaders requestHeaders;
//
//    @Override
//    public Response toResponse(ConstraintViolationException ex) {
//        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
//
//        Response response = null;
//
//        ProblemDetails problemDetail = new ProblemDetails(ex) {
//            @Override
//            protected Response.StatusType fallbackStatus() {
//                return Response.Status.BAD_REQUEST;
//            }
//
//            @Override
//            protected boolean hasDefaultMessage() {
//                return exception.getMessage() != null
//                        && exception.getMessage().equals("HTTP " + getStatus().getStatusCode()
//                        + " " + getStatus().getReasonPhrase());
//            }
//
//            @Override
//            protected String findMediaTypeSubtype() {
//                for (MediaType accept : requestHeaders.getAcceptableMediaTypes()) {
//                    if ("application".equals(accept.getType())) {
//                        return accept.getSubtype();
//                    }
//                }
//                return "json";
//            }
//        };
//
//
//
////        Map<String, String> info = new HashMap<>();
////
////        for (final var constraint : constraintViolations) {
////            info.put(constraint.getPropertyPath().toString(), constraint.getMessage());
////        }
////        detail.setTitle(ex.getClass().toString());
////        detail.setType();    //
////        detail.setInstance(URI.create(uriInfo.getAbsolutePath().getPath()));
////        detail.setStatus(400);
////        detail.setDetail(Constants.PROBLEM_DETAIL_JSON);
////        return Response.status(Response.Status.BAD_REQUEST).entity(detail).build();
//        return Response
//                .status(problemDetail.getStatus())
//                .entity(problemDetail.getBody())
//                .header("Content-Type", problemDetail.getMediaType())
//                .build();
//    }
//}