package exceptions;

public class CommandParseException extends GameException{

	private static final long serialVersionUID = -4296377639717803877L;

	public CommandParseException() {
		super(); 
	}
	
	public CommandParseException(String message){ 
		super(message); 
	}
	
	public CommandParseException(String message, Throwable cause){
		super(message, cause);
	}
	
	public CommandParseException(Throwable cause){ 
		super(cause); 
	}
	
	protected CommandParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
