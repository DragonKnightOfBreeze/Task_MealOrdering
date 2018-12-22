package mealordering.exception;

/**
 * 查询结果（或更新结果）非法时引发的异常
 */
public class ResultInvalidException extends Exception {
	public ResultInvalidException() {
		super();
	}

	public ResultInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResultInvalidException(String message) {
		super(message);
	}

	public ResultInvalidException(Throwable cause) {
		super(cause);
	}
}
