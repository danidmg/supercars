package exceptions;

public class PlayerOutException extends CommandExecuteException{

	private static final long serialVersionUID = -6840249428796907199L;
	
	private static String commonMessage = "Player can´t go out of the board!";
	
	public PlayerOutException() { 
		super(commonMessage); 
	}
	
	public PlayerOutException(String message){ 
		super(message); 
	}
	
	public PlayerOutException(String message, Throwable cause){
		super(message, cause);
	}
	
	public PlayerOutException(Throwable cause){ 
		super(cause); 
	}
	
	protected PlayerOutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
}