package test.ricardo.rpsgame.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import test.ricardo.rpsgame.core.event.domain.DomainEventPublisher;
import test.ricardo.rpsgame.core.game.application.FindAllGamesUseCase;
import test.ricardo.rpsgame.core.game.application.FindGameByIdUseCase;
import test.ricardo.rpsgame.core.game.application.PlayRoundRandomUseCase;
import test.ricardo.rpsgame.core.game.application.StartGameUseCase;
import test.ricardo.rpsgame.core.game.domain.GameRepository;

@Configuration
public class GameCoreBeans {

	@Bean
	StartGameUseCase startGameUseCase(GameRepository gameRepository) {
		return new StartGameUseCase(gameRepository);
	}

	@Bean
	FindGameByIdUseCase findGameByIdUseCase(GameRepository gameRepository) {
		return new FindGameByIdUseCase(gameRepository);
	}

	@Bean
	PlayRoundRandomUseCase playRoundRandomUseCase(GameRepository gameRepository,
			DomainEventPublisher domainEventPublisher) {
		return new PlayRoundRandomUseCase(gameRepository, domainEventPublisher);
	}

	@Bean
	FindAllGamesUseCase findAllGamesUseCase(GameRepository gameRepository) {
		return new FindAllGamesUseCase(gameRepository);
	}
}
