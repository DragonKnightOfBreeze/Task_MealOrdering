package mealordering.exception;

/**
 * 用户未激活时引发的异常
 */
public class UserNotActiveException extends ResultInvalidException {
	public UserNotActiveException() {
		super();
	}

	public UserNotActiveException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotActiveException(String message) {
		super(message);
	}

	public UserNotActiveException(Throwable cause) {
		super(cause);
	}
}
