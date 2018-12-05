package dk_breeze.exception;

/**
 * 未完成的异常
 */
public class ToDoException extends RuntimeException {
	public ToDoException() {
		super();
		System.out.println("未完成的代码！");
	}

	public ToDoException(String message) {
		super(message);
		System.out.println("未完成的代码！");
	}
}
