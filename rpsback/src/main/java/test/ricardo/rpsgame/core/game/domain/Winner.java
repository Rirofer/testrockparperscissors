package test.ricardo.rpsgame.core.game.domain;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Winner {

	PLAYER1, PLAYER2, NONE;

	public static String toStringValues() {
		return Stream.of(Winner.values())
				.map(Winner::name)
				.collect(Collectors.joining(","));
	}
}
