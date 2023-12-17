package test.ricardo.rpsgame.core.game.domain;

import java.util.Random;

public final class MoveRandomGenerator {

	public static Move random() {
		Move[] moves = Move.values();
		int randomNum = new Random().nextInt(moves.length);
		return moves[randomNum];
	}

	private MoveRandomGenerator() {

	}
}
