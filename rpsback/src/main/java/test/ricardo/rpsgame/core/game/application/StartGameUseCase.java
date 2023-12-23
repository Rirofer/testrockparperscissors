package test.ricardo.rpsgame.core.game.application;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import test.ricardo.rpsgame.core.game.domain.Game;
import test.ricardo.rpsgame.core.game.domain.GameRepository;

@Slf4j
public class StartGameUseCase {

	private final GameRepository gameRepository;

	public StartGameUseCase(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	public Game start() {
		Game game = Game.create(UUID.randomUUID());
		log.info("Starting game {}", game.getId());
		return gameRepository.save(game);
	}

}
