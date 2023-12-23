package test.ricardo.rpsgame.core.game.application;

import java.util.List;

import test.ricardo.rpsgame.core.game.domain.Game;
import test.ricardo.rpsgame.core.game.domain.GameRepository;

public class FindAllGamesUseCase {

	private final GameRepository gameRepository;

	public FindAllGamesUseCase(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	public List<Game> findAll() {
		return gameRepository.findAll();
	}

}
