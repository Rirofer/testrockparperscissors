package test.ricardo.rpsgame.adapters.shared.in.http;

public class JsonSerializationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public JsonSerializationException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
}
