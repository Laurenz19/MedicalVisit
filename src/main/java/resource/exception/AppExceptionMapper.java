package resource.exception;

import entity.Message;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


@Provider
public class AppExceptionMapper implements ExceptionMapper<AppException> {

	@Override
	public Response toResponse(AppException exception) {
		
		Message error_message = new Message(exception.getMessage());
		
		return Response.status(exception.getStatus())
				.entity(error_message)
				.build();
	}

}
