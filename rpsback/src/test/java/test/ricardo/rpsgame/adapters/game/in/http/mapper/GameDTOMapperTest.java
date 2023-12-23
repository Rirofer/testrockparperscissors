package test.ricardo.rpsgame.adapters.game.in.http.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import test.ricardo.rpsgame.adapters.game.in.http.dto.GameDTO;
import test.ricardo.rpsgame.adapters.game.in.http.dto.RoundDTO;
import test.ricardo.rpsgame.core.game.domain.Game;
import test.ricardo.rpsgame.core.game.domain.GameMother;
import test.ricardo.rpsgame.core.game.domain.Round;
import test.ricardo.rpsgame.core.game.domain.RoundMother;

class GameDTOMapperTest {

	private GameDTOMapper mapper = new GameDTOMapper();

	@Test
	void testMapGame() {
		List<Round> rounds = new ArrayList<>(List.of(RoundMother.createTie()));
		Game game = GameMother.createWithRounds(rounds);

		GameDTO dto = mapper.map(game);

		Assertions.assertEquals(game.getId(), dto.getId());
		Assertions.assertEquals(game.getWinner(), dto.getWinner());
		Assertions.assertEquals(game.getStatus(), dto.getStatus());
		Assertions.assertEquals(game.getStartedOn(), dto.getStartedOn());
		Assertions.assertEquals(game.getRounds()
				.size(),
				dto.getRounds()
						.size());
		assertEquals(game.getRounds()
				.get(0),
				dto.getRounds()
						.get(0));
		Assertions.assertEquals(game.getStatus(), dto.getStatus());

	}

	private void assertEquals(Round round, RoundDTO dto) {
		Assertions.assertEquals(round.getPlayerOneMove(), dto.playerOneMove());
		Assertions.assertEquals(round.getPlayerTwoMove(), dto.playerTwoMove());
		Assertions.assertEquals(round.isRandomPlayerTwo(), dto.randomPlayerTwo());

	}
}
