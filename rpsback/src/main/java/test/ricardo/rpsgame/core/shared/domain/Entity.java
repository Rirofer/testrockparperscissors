package test.ricardo.rpsgame.core.shared.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import test.ricardo.rpsgame.core.event.domain.DomainEvent;

public abstract class Entity<T> {

	@Getter
	private final T id;
	private final transient List<DomainEvent> domainEvents;

	protected Entity(@NonNull T id) {
		this.id = id;
		domainEvents = new ArrayList<>();
	}

	protected void addDomainEvent(DomainEvent domainEvent) {
		domainEvents.add(domainEvent);
	}

	public List<DomainEvent> getDomainEvents() {
		return new ArrayList<>(domainEvents);
	}
}
