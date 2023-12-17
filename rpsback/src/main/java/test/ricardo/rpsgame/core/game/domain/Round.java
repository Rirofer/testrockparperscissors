package test.ricardo.rpsgame.core.game.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import test.ricardo.rpsgame.core.shared.domain.Assertions;

@Getter
@ToString
@EqualsAndHashCode
public final class Round {

	private final Move playerOneMove;
	private final Move playerTwoMove;
	private final boolean randomPlayerTwo;

	public static Round create(PlayRoundRandomCommand command) {
		Move playerTwoMove = MoveRandomGenerator.random();
		return new Round(command.playerOneMove(), playerTwoMove, true);
	}

	public Round(Move playerOneMove, Move playerTwoMove, boolean randomPlayerTwo) {
		Assertions.assertNotNull(playerOneMove, InvalidRoundException.class, "playerOneMove cannot be null");
		this.playerOneMove = playerOneMove;
		Assertions.assertNotNull(playerTwoMove, InvalidRoundException.class, "playerTwoMove cannot be null");
		this.playerTwoMove = playerTwoMove;
		this.randomPlayerTwo = randomPlayerTwo;
	}

	public Winner setWinner() {
		if (playerOneMove.beats(playerTwoMove)) {
			return Winner.PLAYER1;
		} else if (playerTwoMove.beats(playerOneMove)) {
			return Winner.PLAYER2;
		} else {
			return Winner.NONE;
		}
	}

}
