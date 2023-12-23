package test.ricardo.rpsgame.adapters.game.in.http;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.core.EmbeddedWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.ricardo.rpsgame.adapters.game.in.http.assembler.CollectionGameDTOAssembler;
import test.ricardo.rpsgame.core.game.application.FindAllGamesUseCase;
import test.ricardo.rpsgame.core.game.domain.Game;

@RestController
@RequestMapping(ApiPaths.GAMES)
public class FindAllGamesController {

	private final FindAllGamesUseCase useCase;
	private final CollectionGameDTOAssembler assembler;

	public FindAllGamesController(FindAllGamesUseCase useCase, CollectionGameDTOAssembler assembler) {
		this.useCase = useCase;
		this.assembler = assembler;
	}

	@GetMapping
	public ResponseEntity<CollectionModel<EmbeddedWrapper>> findAll() {
		List<Game> games = useCase.findAll();
		CollectionModel<EmbeddedWrapper> collectionModel = assembler.toCollectionModel(games);
		return ResponseEntity.ok(collectionModel);
	}

}
