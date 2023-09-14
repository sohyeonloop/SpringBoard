package kr.or.ddit.exception;

/**
 * 식별자를 이용해 데이터를 검색했을때, 못찾은 경우, 발생시킬 예외. 
 * : 특정 식별자를 사용하여 데이터를 검색했을 때 데이터를 찾을 수 없는 상황 ex) 회원검색 위해 특정 아이디를 검색
 * 했는데 해당하는 회원이 없는 경우에 이 예외가 발생할 수 있음 
 */
//public class PKNotFoundException extends Exception{ // checked exception
public class PKNotFoundException extends RuntimeException{ // unChecked exception

	public PKNotFoundException() {
		super();
	}

	public PKNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PKNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PKNotFoundException(String message) {
		super(message);
	}

	public PKNotFoundException(Throwable cause) {
		super(cause);
	} 
	
}
