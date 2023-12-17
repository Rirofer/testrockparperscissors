package test.ricardo.rpsgame.core.event.domain;

import java.util.List;

public class DomainEventPublisher {

	private List<DomainEventSubscriber<? extends DomainEvent>> eventSubscribers;

	public DomainEventPublisher(List<DomainEventSubscriber<? extends DomainEvent>> eventSubscribers) {
		this.eventSubscribers = eventSubscribers;
	}

	public void publish(List<DomainEvent> domainEvents) {
		domainEvents.stream().forEach(this::handleEvent);
	}

	private void handleEvent(DomainEvent domainEvent) {
		eventSubscribers.forEach(l -> l.onDomainEvent(domainEvent));
	}

}
