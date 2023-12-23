package test.ricardo.rpsgame.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import test.ricardo.rpsgame.core.event.domain.DomainEventPublisher;
import test.ricardo.rpsgame.core.event.domain.DomainEventSubscriber;

@Configuration
public class DomainEventBeans {

	@Bean
	DomainEventPublisher domainEventPublisher(List<DomainEventSubscriber<?>> domainEventSubscribers) {
		return new DomainEventPublisher(domainEventSubscribers);
	}
}
