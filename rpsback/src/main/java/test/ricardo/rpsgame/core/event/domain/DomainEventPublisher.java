package test.ricardo.rpsgame.core.event.domain;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DomainEventPublisher {

	private final List<DomainEventSubscriber<? extends DomainEvent>> eventSubscribers;

	public DomainEventPublisher(List<DomainEventSubscriber<? extends DomainEvent>> eventSubscribers) {
		this.eventSubscribers = eventSubscribers;
	}

	public void publish(List<DomainEvent> domainEvents) {
		log.info("Publishing domain events {}", domainEvents);
		domainEvents.stream()
				.forEach(this::handleEvent);
	}

	private void handleEvent(DomainEvent domainEvent) {
		eventSubscribers.forEach(l -> l.onDomainEvent(domainEvent));
	}

}
