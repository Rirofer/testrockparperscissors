package test.ricardo.rpsgame.core.game.domain;

public class IncorrectGameStatusException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IncorrectGameStatusException(String msg) {
		super(msg);
	}
}
