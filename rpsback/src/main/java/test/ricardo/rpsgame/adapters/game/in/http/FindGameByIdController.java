package test.ricardo.rpsgame.adapters.game.in.http;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.ricardo.rpsgame.adapters.game.in.http.assembler.GameDTOAssembler;
import test.ricardo.rpsgame.adapters.game.in.http.dto.GameDTO;
import test.ricardo.rpsgame.core.game.application.FindGameByIdUseCase;
import test.ricardo.rpsgame.core.game.domain.Game;

@RestController
@RequestMapping(ApiPaths.GAMES)
public class FindGameByIdController {

	private final FindGameByIdUseCase useCase;
	private final GameDTOAssembler assembler;

	public FindGameByIdController(FindGameByIdUseCase useCase, GameDTOAssembler assembler) {
		this.useCase = useCase;
		this.assembler = assembler;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<GameDTO> findById(@PathVariable UUID id) {
		Game game = useCase.findById(id);
		GameDTO dto = assembler.toModel(game);
		return ResponseEntity.ok(dto);
	}
}
