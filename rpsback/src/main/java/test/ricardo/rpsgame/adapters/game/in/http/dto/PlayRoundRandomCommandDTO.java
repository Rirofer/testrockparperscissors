package test.ricardo.rpsgame.adapters.game.in.http.dto;

import jakarta.validation.constraints.NotNull;
import test.ricardo.rpsgame.core.game.domain.Move;

public record PlayRoundRandomCommandDTO(@NotNull Move playerOneMove) {

}
