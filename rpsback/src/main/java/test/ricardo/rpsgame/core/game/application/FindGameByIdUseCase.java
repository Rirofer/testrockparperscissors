package test.ricardo.rpsgame.core.game.application;

import java.util.UUID;

import test.ricardo.rpsgame.core.game.domain.Game;
import test.ricardo.rpsgame.core.game.domain.GameRepository;
import test.ricardo.rpsgame.core.shared.application.NotFoundException;

public class FindGameByIdUseCase {

	private final GameRepository gameRepository;

	public FindGameByIdUseCase(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	public Game findById(UUID id) {
		return gameRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Game with id " + id + " not found"));
	}
}
