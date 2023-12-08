package test.ricardo.rpsgame.core.event.domain;

import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public abstract class DomainEvent {

	private final LocalDate occurredOn;
	private final String aggregateId;
	private final String type;

	public DomainEvent(LocalDate occurredOn, String aggregateId, String type) {
		this.occurredOn = occurredOn;
		this.aggregateId = aggregateId;
		this.type = type;
	}

}
