package test.ricardo.rpsgame.core.game.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameFinishedTest {

	@Test
	void testCreate() {
		Game game = GameMother.create();
		GameFinished gameFinished = GameFinished.create(game);

		Assertions.assertEquals(GameFinished.TYPE, gameFinished.getType());
		Assertions.assertEquals(game.getId().toString(), gameFinished.getAggregateId());
		Assertions.assertEquals(game.getRounds(), gameFinished.getRounds());
		Assertions.assertEquals(game.getWinner(), gameFinished.getWinner());
		Assertions.assertNotNull(gameFinished.getOccurredOn());
	}
	
}
