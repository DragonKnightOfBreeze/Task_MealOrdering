package dk_breeze.exception;

/**
 * 未完成的异常
 */
public class NotImplementedException extends RuntimeException {
	public NotImplementedException() {
		super();
		System.out.println("未完成的代码！");
	}

	public NotImplementedException(String message) {
		super(message);
		System.out.println("未完成的代码！");
	}
}
