package test.ricardo.rpsgame.core.game.domain;

import java.util.UUID;

public final class GameMother {

	public static Game create() {
		return new Game(UUID.randomUUID());
	}

	private GameMother() {

	}
}
