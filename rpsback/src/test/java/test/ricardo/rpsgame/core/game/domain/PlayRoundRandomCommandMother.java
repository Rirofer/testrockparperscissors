package test.ricardo.rpsgame.core.game.domain;

import java.util.UUID;

public final class PlayRoundRandomCommandMother {

	public static PlayRoundRandomCommand create() {
		return new PlayRoundRandomCommand(UUID.randomUUID(), Move.PAPER);
	}

	public static PlayRoundRandomCommand create(UUID gameId, Move playerOneMove) {
		return new PlayRoundRandomCommand(gameId, playerOneMove);
	}

	private PlayRoundRandomCommandMother() {

	}
}
