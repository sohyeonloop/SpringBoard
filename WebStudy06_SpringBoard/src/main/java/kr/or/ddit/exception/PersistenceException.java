package kr.or.ddit.exception;

/**
 * 
 * 	Persistence Layer 에서 발생한 SQLException의 wrapper<adapter> exception
 * 
 */
public class PersistenceException extends RuntimeException{

	public PersistenceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersistenceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PersistenceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PersistenceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
