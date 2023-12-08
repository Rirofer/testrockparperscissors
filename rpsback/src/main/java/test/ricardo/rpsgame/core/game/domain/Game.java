package test.ricardo.rpsgame.core.game.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import lombok.Getter;
import lombok.NonNull;
import test.ricardo.rpsgame.core.shared.domain.Entity;

public class Game extends Entity<UUID> {

	private List<Round> rounds;
	@Getter
	private Winner winner;
	@Getter
	private Status status;

	public static Game create(@NonNull UUID id) {
		return new Game(id);
	}

	public Game(UUID id) {
		super(id);
		rounds = new ArrayList<>();
		status = Status.ONGOING;
		winner = Winner.NONE;
	}

	public void playRound(Round round) {
		addRound(round);
		winner = round.setWinner();
		if (isThereAWinner()) {
			finish();
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
	}

}
