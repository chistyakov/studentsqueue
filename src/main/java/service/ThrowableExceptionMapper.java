package service;

import java.util.Arrays;
import javax.json.Json;
import javax.json.JsonObject;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Produces("application/json")
public class ThrowableExceptionMapper implements ExceptionMapper<Throwable> {
    private static final int STACKTRACELENGTH = 3;
    
    @Override
    public Response toResponse(Throwable exception) {
        String message = exception.getMessage();
        StackTraceElement[] stackTraceRange = Arrays.copyOfRange(
                exception.getStackTrace(), 0, STACKTRACELENGTH);
        String stackTrace = Arrays.toString(stackTraceRange)
                                  .replaceAll(",", "\n");
        JsonObject problemJson = Json.createObjectBuilder()
                                     .add("type", "error")
                                     .add("title", message)
                                     .add("detail", stackTrace)
                                     .build();
        
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                       .entity(problemJson.toString())
                       .build();
    }
}