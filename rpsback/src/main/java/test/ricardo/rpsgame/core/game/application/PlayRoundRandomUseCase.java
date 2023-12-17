package test.ricardo.rpsgame.core.game.application;

import lombok.extern.slf4j.Slf4j;
import test.ricardo.rpsgame.core.event.domain.DomainEventPublisher;
import test.ricardo.rpsgame.core.game.domain.Game;
import test.ricardo.rpsgame.core.game.domain.GameRepository;
import test.ricardo.rpsgame.core.game.domain.PlayRoundRandomCommand;
import test.ricardo.rpsgame.core.game.domain.Round;
import test.ricardo.rpsgame.core.shared.application.NotFoundException;

@Slf4j
public class PlayRoundRandomUseCase {

	private GameRepository gameRepository;
	private DomainEventPublisher domainEventPublisher;

	public PlayRoundRandomUseCase(GameRepository gameRepository, DomainEventPublisher domainEventPublisher) {
		this.gameRepository = gameRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	public Game playRound(PlayRoundRandomCommand command) {
		log.info("Playing round {}", command);
		Game game = gameRepository.findById(command.gameId())
				.orElseThrow(() -> new NotFoundException("Game with id " + command.gameId() + " not found"));
		Round round = Round.create(command);
		game.playRound(round);
		game = gameRepository.save(game);
		domainEventPublisher.publish(game.getDomainEvents());
		return game;
	}

}
