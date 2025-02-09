package exceptions;

public class GameException extends Exception{

	private static final long serialVersionUID = 6693718474605465191L;

	public GameException() { 
		super(); 
	}
	
	public GameException(String message){ 
		super(message); 
	}
	
	public GameException(String message, Throwable cause){
		super(message, cause);
	}
	
	public GameException(Throwable cause){ super(cause); }
	
	protected GameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
