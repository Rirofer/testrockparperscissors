package test.ricardo.rpsgame.adapters.game.in.http;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.ricardo.rpsgame.adapters.game.in.http.assembler.GameDTOAssembler;
import test.ricardo.rpsgame.adapters.game.in.http.dto.GameDTO;
import test.ricardo.rpsgame.core.game.application.StartGameUseCase;
import test.ricardo.rpsgame.core.game.domain.Game;

@RestController
@RequestMapping(ApiPaths.START_GAME)
public class StartGameController {

	private final StartGameUseCase useCase;
	private final GameDTOAssembler assembler;

	public StartGameController(StartGameUseCase useCase, GameDTOAssembler assembler) {
		this.useCase = useCase;
		this.assembler = assembler;
	}

	@PostMapping(produces = MediaTypes.HAL_FORMS_JSON_VALUE)
	public ResponseEntity<GameDTO> startGame() {
		Game game = useCase.start();
		GameDTO dto = assembler.toModel(game);
		return ResponseEntity.ok(dto);
	}

}
