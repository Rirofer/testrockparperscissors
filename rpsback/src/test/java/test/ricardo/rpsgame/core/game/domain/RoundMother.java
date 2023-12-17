package test.ricardo.rpsgame.core.game.domain;

public final class RoundMother {

	public static Round create(Move playerOneMove, Move playerTwoMove) {
		return new Round(playerOneMove, playerTwoMove, false);
	}

	public static Round createPlayerOneWins() {
		return new Round(Move.PAPER, Move.ROCK, false);

	}

	public static Round createTie() {
		return new Round(Move.PAPER, Move.PAPER, false);

	}

	private RoundMother() {

	}
}
