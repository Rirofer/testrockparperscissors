package test.ricardo.rpsgame.core.game.domain;

public class InvalidRoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidRoundException(String msg) {
		super(msg);
	}
}
