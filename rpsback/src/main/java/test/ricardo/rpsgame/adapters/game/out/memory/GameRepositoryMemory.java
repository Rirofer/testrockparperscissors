package test.ricardo.rpsgame.adapters.game.out.memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import test.ricardo.rpsgame.core.game.domain.Game;
import test.ricardo.rpsgame.core.game.domain.GameRepository;

@Component
public class GameRepositoryMemory implements GameRepository {

	private final Map<UUID, Game> repository;

	public GameRepositoryMemory() {
		repository = new HashMap<>();
	}

	@Override
	public Optional<Game> findById(UUID id) {
		if (repository.containsKey(id)) {
			return Optional.of(repository.get(id));
		}
		return Optional.empty();
	}

	@Override
	public Game save(Game game) {
		repository.put(game.getId(), game);
		return game;
	}

	@Override
	public List<Game> findAll() {
		return repository.entrySet()
				.stream()
				.map(Entry::getValue)
				.sorted(this::sortByStartedOn)
				.collect(Collectors.toList());
	}

	private int sortByStartedOn(Game game1, Game game2) {
		return game1.getStartedOn()
				.compareTo(game2.getStartedOn());
	}

	@Override
	public void deleteAll() {
		repository.clear();
	}

}
