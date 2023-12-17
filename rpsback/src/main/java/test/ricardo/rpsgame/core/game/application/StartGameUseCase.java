package test.ricardo.rpsgame.core.game.application;

import java.util.UUID;

import test.ricardo.rpsgame.core.game.domain.Game;
import test.ricardo.rpsgame.core.game.domain.GameRepository;

public class StartGameUseCase {

	private GameRepository gameRepository;

	public StartGameUseCase(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	public Game start() {
		Game game = Game.create(UUID.randomUUID());
		return gameRepository.save(game);
	}

}
