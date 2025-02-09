package exceptions;

public class CommandExecuteException extends GameException{

	private static final long serialVersionUID = 5007138724092939807L;
	
	private static String commonMessage = "Unable to execute command";
	
	public CommandExecuteException() { 
		super(commonMessage); 
	}
	
	public CommandExecuteException(String message){ 
		super(message); 
	}
	
	public CommandExecuteException(String message, Throwable cause){
		super(message, cause);
	}
	
	public CommandExecuteException(Throwable cause){ 
		super(cause); 
	}
	
	protected CommandExecuteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
