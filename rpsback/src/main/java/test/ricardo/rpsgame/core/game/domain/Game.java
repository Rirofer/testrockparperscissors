package test.ricardo.rpsgame.core.game.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.NonNull;
import test.ricardo.rpsgame.core.shared.domain.Entity;

public class Game extends Entity<UUID> {

	private final List<Round> rounds;
	@Getter
	private Winner winner;
	@Getter
	private Status status;
	@Getter
	private final LocalDateTime startedOn;

	public static Game create(@NonNull UUID id) {
		return new Game(id, LocalDateTime.now(), new ArrayList<>(), Status.ONGOING, Winner.NONE);
	}

	public Game(UUID id, LocalDateTime startedOn, List<Round> rounds, Status status, Winner winner) {
		super(id);
		this.rounds = rounds;
		this.status = status;
		this.winner = winner;
		this.startedOn = startedOn;
	}

	public void playRound(Round round) {
		validateStatus();
		addRound(round);
		winner = round.setWinner();
		if (isThereAWinner()) {
			finish();
		}

	}

	private void validateStatus() {
		if (Status.ONGOING != getStatus()) {
			throw new IncorrectGameStatusException("Game is not in status " + Status.ONGOING);
		}
	}

	private boolean isThereAWinner() {
		return winner != Winner.NONE;
	}

	private void finish() {
		status = Status.FINIHSED;
		GameFinished gameFinished = GameFinished.create(this);
		addDomainEvent(gameFinished);
	}

	private void addRound(Round round) {
		rounds.add(round);
	}

	public List<Round> getRounds() {
		return new ArrayList<>(rounds);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		return Objects.equals(getId(), other.getId());
	}

	public static enum Status {
		ONGOING, FINIHSED;

		public static String toStringValues() {
			return Stream.of(Status.values())
					.map(Status::name)
					.collect(Collectors.joining(","));
		}
	}

}
