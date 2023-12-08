package test.ricardo.rpsgame.core.game.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import test.ricardo.rpsgame.core.shared.domain.Assertions;

@Getter
@EqualsAndHashCode
public final class Round {

	private final Move playerOneMove;
	private final Move playerTwoMove;

	public Round(Move playerOneMove, Move playerTwoMove) {
		Assertions.assertNotNull(playerOneMove, InvalidRoundException.class, "playerOneMove cannot be null");
		this.playerOneMove = playerOneMove;
		Assertions.assertNotNull(playerTwoMove, InvalidRoundException.class, "playerTwoMove cannot be null");
		this.playerTwoMove = playerTwoMove;
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
