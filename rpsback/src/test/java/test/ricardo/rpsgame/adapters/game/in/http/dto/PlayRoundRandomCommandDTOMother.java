package test.ricardo.rpsgame.adapters.game.in.http.dto;

import test.ricardo.rpsgame.core.game.domain.Move;

public final class PlayRoundRandomCommandDTOMother {

	public static PlayRoundRandomCommandDTO create(Move playerOneMove) {
		return new PlayRoundRandomCommandDTO(playerOneMove);
	}

	private PlayRoundRandomCommandDTOMother() {

	}
}
