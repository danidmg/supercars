package exceptions;

public class NotEnoughCoinsException extends CommandExecuteException{

	private static final long serialVersionUID = 6068194340161808799L;
	
	private static String commonMessage = "Not enough coins";
	
	public NotEnoughCoinsException() { 
		super(commonMessage); 
	}
	
	public NotEnoughCoinsException(String message){ 
		super(message); 
	}
	
	public NotEnoughCoinsException(String message, Throwable cause){
		super(message, cause);
	}
	
	public NotEnoughCoinsException(Throwable cause){ 
		super(cause); 
	}
	
	protected NotEnoughCoinsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
}