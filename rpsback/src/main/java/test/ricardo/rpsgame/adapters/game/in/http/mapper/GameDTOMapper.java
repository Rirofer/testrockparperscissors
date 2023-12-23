package test.ricardo.rpsgame.adapters.game.in.http.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import test.ricardo.rpsgame.adapters.game.in.http.dto.GameDTO;
import test.ricardo.rpsgame.adapters.game.in.http.dto.RoundDTO;
import test.ricardo.rpsgame.core.game.domain.Game;
import test.ricardo.rpsgame.core.game.domain.Round;

@Component
public class GameDTOMapper {

	public GameDTO map(Game game) {
		List<RoundDTO> rounds = game.getRounds()
				.stream()
				.map(this::mapRound)
				.collect(Collectors.toList());
		return new GameDTO(game.getId(), rounds, game.getWinner(), game.getStatus(), game.getStartedOn());
	}

	private RoundDTO mapRound(Round round) {
		return new RoundDTO(round.getPlayerOneMove(), round.getPlayerTwoMove(), round.isRandomPlayerTwo());
	}
}
