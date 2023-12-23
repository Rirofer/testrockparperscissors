package test.ricardo.rpsgame.adapters.game.in.http.dto;

import test.ricardo.rpsgame.core.game.domain.Move;

public record RoundDTO(Move playerOneMove, Move playerTwoMove, boolean randomPlayerTwo) {

}
