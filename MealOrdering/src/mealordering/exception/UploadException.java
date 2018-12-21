package mealordering.exception;

/**
 * 文件上传出错时引发的异常
 */
public class UploadException extends Exception {
	public UploadException() {
		super();
	}

	public UploadException(String message, Throwable cause) {
		super(message, cause);
	}

	public UploadException(String message) {
		super(message);
	}

	public UploadException(Throwable cause) {
		super(cause);
	}
}
