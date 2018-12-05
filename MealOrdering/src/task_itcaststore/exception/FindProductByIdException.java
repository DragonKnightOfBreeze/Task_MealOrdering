package task_itcaststore.exception;

/**
 * 根据ID查找商品时的异常
 */
public class FindProductByIdException extends Exception {

	private static final long serialVersionUID = 1L;

	public FindProductByIdException() {
		super();
	}

	public FindProductByIdException(String message, Throwable cause) {
		super(message, cause);
	}

	public FindProductByIdException(String message) {
		super(message);
	}

	public FindProductByIdException(Throwable cause) {
		super(cause);
	}

}
