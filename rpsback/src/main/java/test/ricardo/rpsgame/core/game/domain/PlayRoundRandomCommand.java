package test.ricardo.rpsgame.core.game.domain;

import java.util.UUID;

public record PlayRoundRandomCommand(UUID gameId, Move playerOneMove) {

}
