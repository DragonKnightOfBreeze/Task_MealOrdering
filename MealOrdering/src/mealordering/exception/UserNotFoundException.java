package mealordering.exception;

/**
 * 用户未找到时引发的异常
 */
public class UserNotFoundException extends ResultEmptyException {
	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
	}
}
