package test.ricardo.rpsgame.core.game.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameRepository {

	Optional<Game> findById(UUID id);

	Game save(Game game);

	List<Game> findAll();
}
