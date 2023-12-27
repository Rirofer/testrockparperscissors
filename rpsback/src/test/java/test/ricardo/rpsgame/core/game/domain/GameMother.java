package test.ricardo.rpsgame.core.game.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import test.ricardo.rpsgame.core.game.domain.Game.Status;

public final class GameMother {

	public static Game create() {
		return createStartedOn(LocalDateTime.now());
	}

	public static Game create(UUID id) {
		return new Game(id, LocalDateTime.now(), new ArrayList<>(), Status.ONGOING, Winner.NONE);
	}

	public static Game createStartedOn(LocalDateTime createdOn) {
		return new Game(UUID.randomUUID(), createdOn, new ArrayList<>(), Status.ONGOING, Winner.NONE);
	}

	public static Game createWithRounds(List<Round> rounds) {
		return new Game(UUID.randomUUID(), LocalDateTime.now(), rounds, Status.ONGOING, Winner.NONE);
	}

	public static Game createFinished() {
		return new Game(UUID.randomUUID(), LocalDateTime.now(),
				new ArrayList<>(List.of(RoundMother.createPlayerOneWins())), Status.FINISHED, Winner.PLAYER1);
	}

	private GameMother() {

	}
}
