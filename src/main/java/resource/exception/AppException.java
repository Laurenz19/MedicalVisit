package resource.exception;

public class AppException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5036321829236150422L;
	
	private String message;
	private int status;
	
	public AppException(String message, int status) {
		super();
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}	
}
