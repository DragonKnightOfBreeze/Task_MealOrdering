package mealordering.exception;

/**
 * 激活用户时的异常
 */
public class ActiveException extends Exception {
	private static final long serialVersionUID = 1L;

	public ActiveException() {
		super();
	}

	public ActiveException(String message, Throwable cause) {
		super(message, cause);
	}

	public ActiveException(String message) {
		super(message);
	}

	public ActiveException(Throwable cause) {
		super(cause);
	}

}
