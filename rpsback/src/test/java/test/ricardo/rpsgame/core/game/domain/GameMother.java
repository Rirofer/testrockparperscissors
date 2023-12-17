package test.ricardo.rpsgame.core.game.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public final class GameMother {

	public static Game create() {
		return createOn(LocalDateTime.now());
	}

	public static Game create(UUID id) {
		return new Game(id, LocalDateTime.now());
	}

	public static Game createOn(LocalDateTime createdOn) {
		return new Game(UUID.randomUUID(), createdOn);
	}

	private GameMother() {

	}
}
