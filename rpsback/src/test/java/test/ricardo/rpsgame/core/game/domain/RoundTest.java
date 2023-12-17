package test.ricardo.rpsgame.core.game.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RoundTest {

	@Test
	void testRockBeatsSciccors() {
		Round round = RoundMother.create(Move.ROCK, Move.SCICCORS);
		Assertions.assertEquals(Winner.PLAYER1, round.setWinner());
	}

	@Test
	void testSciccorsBeatsPaper() {
		Round round = RoundMother.create(Move.SCICCORS, Move.PAPER);
		Assertions.assertEquals(Winner.PLAYER1, round.setWinner());
	}

	@Test
	void testPaperBeatsRock() {
		Round round = RoundMother.create(Move.PAPER, Move.ROCK);
		Assertions.assertEquals(Winner.PLAYER1, round.setWinner());
	}

	@Test
	void testTie() {
		Round round = RoundMother.create(Move.PAPER, Move.PAPER);
		Assertions.assertEquals(Winner.NONE, round.setWinner());
	}

	@Test
	void tesPlayerTwoWins() {
		Round round = RoundMother.create(Move.SCICCORS, Move.ROCK);
		Assertions.assertEquals(Winner.PLAYER2, round.setWinner());
	}

	@Test
	void testThrowIllegalRoundExceptionWhenMovesNull() {
		Assertions.assertThrows(InvalidRoundException.class, () -> RoundMother.create(null, Move.ROCK));
		Assertions.assertThrows(InvalidRoundException.class, () -> RoundMother.create(Move.SCICCORS, null));

	}

	@Test
	void testCreateFromCommand() {
		PlayRoundRandomCommand command = PlayRoundRandomCommandMother.create();

		Round round = Round.create(command);

		Assertions.assertEquals(command.playerOneMove(), round.getPlayerOneMove());
	}

}
