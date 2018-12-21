package mealordering.exception;

/**
 * 操作超时时引发的异常
 */
public class TimeoutException extends Exception {
	public TimeoutException() {
		super();
	}

	public TimeoutException(String message, Throwable cause) {
		super(message, cause);
	}

	public TimeoutException(String message) {
		super(message);
	}

	public TimeoutException(Throwable cause) {
		super(cause);
	}
}
