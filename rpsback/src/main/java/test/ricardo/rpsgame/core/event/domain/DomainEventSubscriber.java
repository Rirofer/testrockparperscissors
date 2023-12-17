package test.ricardo.rpsgame.core.event.domain;

import java.util.Objects;

public interface DomainEventSubscriber<T extends DomainEvent> {

	default void onDomainEvent(DomainEvent domainEvent) {
		if (!Objects.isNull(domainEvent) && getEventClass().isInstance(domainEvent)) {
			T event = getEventClass().cast(domainEvent);
			handle(event);
		}
	}

	void handle(T event);

	Class<T> getEventClass();

}
