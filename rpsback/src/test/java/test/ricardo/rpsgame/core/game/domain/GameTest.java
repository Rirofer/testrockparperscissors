package test.ricardo.rpsgame.core.game.domain;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import test.ricardo.rpsgame.core.event.domain.DomainEvent;
import test.ricardo.rpsgame.core.game.domain.Game.Status;

class GameTest {

	@Test
	@DisplayName("givenRoundWithWinner_whenPlayingRound_thenShouldFinishGameAndAddRound")
	void testPlayRoundWithWinner() {
		Game game = GameMother.create();
		Round round = RoundMother.createPlayerOneWins();

		game.playRound(round);

		Assertions.assertEquals(Status.FINISHED, game.getStatus());
		Assertions.assertEquals(Winner.PLAYER1, game.getWinner());
		List<DomainEvent> domainEvents = game.getDomainEvents();
		Assertions.assertEquals(1, domainEvents.size());
		Assertions.assertEquals(GameFinished.TYPE, domainEvents.get(0).getType());
		List<Round> rounds = game.getRounds();
		Assertions.assertEquals(1, rounds.size());
		Assertions.assertEquals(round, rounds.get(0));

	}

	@Test
	@DisplayName("givenRoundWithTie_whenPlayingRound_thenShouldNotFinishGameAndAddRound")
	void testPlayRoundWithTie() {
		Game game = GameMother.create();
		Round round = RoundMother.createTie();

		game.playRound(round);

		Assertions.assertEquals(Status.ONGOING, game.getStatus());
		Assertions.assertEquals(Winner.NONE, game.getWinner());
		Assertions.assertEquals(0, game.getDomainEvents().size());
		List<Round> rounds = game.getRounds();
		Assertions.assertEquals(1, rounds.size());
		Assertions.assertEquals(round, rounds.get(0));
	}
}
