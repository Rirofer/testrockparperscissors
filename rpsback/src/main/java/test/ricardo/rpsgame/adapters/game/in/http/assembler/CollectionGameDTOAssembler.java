package test.ricardo.rpsgame.adapters.game.in.http.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mediatype.Affordances;
import org.springframework.hateoas.server.core.EmbeddedWrapper;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import test.ricardo.rpsgame.adapters.game.in.http.FindAllGamesController;
import test.ricardo.rpsgame.adapters.game.in.http.StartGameController;
import test.ricardo.rpsgame.adapters.game.in.http.dto.GameDTO;
import test.ricardo.rpsgame.adapters.shared.in.http.CollectionResourceUtils;
import test.ricardo.rpsgame.core.game.domain.Game;

@Component
public class CollectionGameDTOAssembler {

	public static final String EMBEDDED_REL = "games";
	public static final String START_GAME_AFFORDANCE = "start-game-command";

	private final GameDTOAssembler assembler;

	public CollectionGameDTOAssembler(GameDTOAssembler assembler) {
		this.assembler = assembler;
	}

	public CollectionModel<EmbeddedWrapper> toCollectionModel(List<Game> games) {
		List<GameDTO> dtos = toGameDTOs(games);
		CollectionModel<EmbeddedWrapper> collectionModel = CollectionResourceUtils.wrap(dtos, EMBEDDED_REL);
		addLinks(collectionModel);
		return collectionModel;
	}

	private List<GameDTO> toGameDTOs(List<Game> games) {
		return games.stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
	}

	private void addLinks(CollectionModel<EmbeddedWrapper> collectionModel) {
		Link self = linkTo(methodOn(FindAllGamesController.class).findAll()).withSelfRel();
		collectionModel.add(getAffordances(self));
	}

	private Link getAffordances(Link self) {
		return Affordances.of(self)
				.afford(HttpMethod.PUT)
				.andAfford(HttpMethod.POST)
				.withTarget(startGameLink())
				.withName(START_GAME_AFFORDANCE)
				.withOutput(GameDTO.class)
				.toLink();
	}

	private Link startGameLink() {
		return linkTo(methodOn(StartGameController.class).startGame()).withRel(START_GAME_AFFORDANCE);
	}

}
