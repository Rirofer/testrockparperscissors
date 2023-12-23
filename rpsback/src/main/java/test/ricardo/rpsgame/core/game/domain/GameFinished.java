package test.ricardo.rpsgame.core.game.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import test.ricardo.rpsgame.core.event.domain.DomainEvent;

@Getter
public final class GameFinished extends DomainEvent {

	public static final String TYPE = "GameFinished";

	private final Winner winner;
	private final List<Round> rounds;

	public static GameFinished create(Game game) {
		return new GameFinished(LocalDate.now(), game.getId()
				.toString(), game.getWinner(), game.getRounds());
	}

	public GameFinished(LocalDate occurredOn, String aggregateId, Winner winner, List<Round> rounds) {
		super(occurredOn, aggregateId, TYPE);
		this.winner = winner;
		this.rounds = List.copyOf(rounds);
	}

	@Override
	public String toString() {
		return "GameFinished [winner=" + winner + ", rounds=" + rounds + ", getOccurredOn()=" + getOccurredOn()
				+ ", getAggregateId()=" + getAggregateId() + ", getType()=" + getType() + "]";
	}

}
