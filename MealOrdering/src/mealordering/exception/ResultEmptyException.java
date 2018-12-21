package mealordering.exception;

/**
 * 查询结果为空时引发的异常
 */
public class ResultEmptyException extends Exception {
	public ResultEmptyException() {
		super();
	}

	public ResultEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResultEmptyException(String message) {
		super(message);
	}

	public ResultEmptyException(Throwable cause) {
		super(cause);
	}
}
