package test.ricardo.rpsgame.adapters.game.in.http;

import java.util.UUID;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import test.ricardo.rpsgame.adapters.game.in.http.assembler.GameDTOAssembler;
import test.ricardo.rpsgame.adapters.game.in.http.dto.GameDTO;
import test.ricardo.rpsgame.adapters.game.in.http.dto.PlayRoundRandomCommandDTO;
import test.ricardo.rpsgame.core.game.application.PlayRoundRandomUseCase;
import test.ricardo.rpsgame.core.game.domain.Game;
import test.ricardo.rpsgame.core.game.domain.PlayRoundRandomCommand;

@RestController
@RequestMapping(ApiPaths.GAMES)
public class PlayRoundRandomController {

	private final PlayRoundRandomUseCase useCase;
	private final GameDTOAssembler assembler;

	public PlayRoundRandomController(PlayRoundRandomUseCase useCase, GameDTOAssembler assembler) {
		this.useCase = useCase;
		this.assembler = assembler;
	}

	@PostMapping(value = "/{gameId}" + ApiPaths.PLAY_ROUND_RANDOM, produces = MediaTypes.HAL_FORMS_JSON_VALUE)
	public ResponseEntity<GameDTO> playRoundRandom(@PathVariable UUID gameId,
			@Valid @RequestBody PlayRoundRandomCommandDTO commandDTO) {
		PlayRoundRandomCommand command = new PlayRoundRandomCommand(gameId, commandDTO.playerOneMove());
		Game game = useCase.playRound(command);
		GameDTO dto = assembler.toModel(game);
		return ResponseEntity.ok(dto);
	}

}
