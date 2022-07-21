package kr.ror.common.exception;

public class InvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidException() {

	}

	public InvalidException(String s) {
		super(s);
	}

	public InvalidException(Throwable throwable) {
		super(throwable);
	}

	public InvalidException(String s, Throwable throwable) {
		super(s, throwable);
	}
}
