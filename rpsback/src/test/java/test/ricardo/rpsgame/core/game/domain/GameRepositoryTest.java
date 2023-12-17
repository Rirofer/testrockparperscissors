package test.ricardo.rpsgame.core.game.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import test.ricardo.rpsgame.adapters.game.out.memory.GameRepositoryMemory;

class GameRepositoryTest {

	private GameRepository gameRepository;

	@BeforeEach
	void setUp() {
		gameRepository = create();
	}

	@Test
	void testFindById() {
		Game game = GameMother.create(UUID.randomUUID());
		gameRepository.save(game);

		Optional<Game> optional = gameRepository.findById(game.getId());

		Assertions.assertTrue(optional.isPresent());
		Game res = optional.get();
		Assertions.assertEquals(game, res);
	}

	@Test
	void testFindByIdNotExistingShouldReturnEmpty() {
		Game game = GameMother.create(UUID.randomUUID());

		Optional<Game> optional = gameRepository.findById(game.getId());

		Assertions.assertTrue(optional.isEmpty());

	}

	@Test
	void testFindAllShouldReturnAllSortedByCreatedOn() {
		List<Game> games = new ArrayList<>(List.of(GameMother.createOn(LocalDateTime.now().plusSeconds(10)),
				GameMother.createOn(LocalDateTime.now().plusSeconds(1)),
				GameMother.createOn(LocalDateTime.now().plusSeconds(100))));
		games.forEach(gameRepository::save);

		List<Game> res = gameRepository.findAll();

		games.sort((g1, g2) -> g1.getCreatedOn().compareTo(g2.getCreatedOn()));
		Assertions.assertEquals(games, res);
	}

	private GameRepository create() {
		return new GameRepositoryMemory();
	}
}
