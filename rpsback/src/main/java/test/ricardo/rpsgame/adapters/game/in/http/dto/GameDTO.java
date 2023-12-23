package test.ricardo.rpsgame.adapters.game.in.http.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import test.ricardo.rpsgame.core.game.domain.Game.Status;
import test.ricardo.rpsgame.core.game.domain.Winner;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO extends RepresentationModel<GameDTO> {

	private UUID id;
	private List<RoundDTO> rounds;
	private Winner winner;
	private Status status;
	private LocalDateTime startedOn;
}
