package task_itcaststore.exception;

/**
 * 列出商品时的异常
 */
public class ListProductException extends Exception {

	private static final long serialVersionUID = 1L;

	public ListProductException() {
		super();
	}

	public ListProductException(String message, Throwable cause) {
		super(message, cause);
	}

	public ListProductException(String message) {
		super(message);
	}

	public ListProductException(Throwable cause) {
		super(cause);
	}

}
