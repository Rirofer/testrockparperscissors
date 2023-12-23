package test.ricardo.rpsgame.adapters.game.in.http.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mediatype.Affordances;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import test.ricardo.rpsgame.adapters.game.in.http.FindGameByIdController;
import test.ricardo.rpsgame.adapters.game.in.http.PlayRoundRandomController;
import test.ricardo.rpsgame.adapters.game.in.http.dto.GameDTO;
import test.ricardo.rpsgame.adapters.game.in.http.dto.PlayRoundRandomCommandDTO;
import test.ricardo.rpsgame.adapters.game.in.http.mapper.GameDTOMapper;
import test.ricardo.rpsgame.core.game.domain.Game;

@Component
public class GameDTOAssembler extends RepresentationModelAssemblerSupport<Game, GameDTO> {

	public static final String PLAY_RANDOM_ROUND_AFFORDANCE = "play-random-round-command";
	private final GameDTOMapper mapper;

	public GameDTOAssembler(GameDTOMapper mapper) {
		super(FindGameByIdController.class, GameDTO.class);
		this.mapper = mapper;
	}

	@Override
	public GameDTO toModel(Game entity) {
		GameDTO dto = mapper.map(entity);
		addLinks(dto);
		return dto;
	}

	private void addLinks(GameDTO dto) {
		Link self = linkTo(methodOn(FindGameByIdController.class).findById(dto.getId())).withSelfRel();
		dto.add(getAffordances(dto, self));
	}

	private Link getAffordances(GameDTO dto, Link self) {
		return Affordances.of(self)
				.afford(HttpMethod.PUT)
				.andAfford(HttpMethod.POST)
				.withInput(PlayRoundRandomCommandDTO.class)
				.withOutput(GameDTO.class)
				.withName(PLAY_RANDOM_ROUND_AFFORDANCE)
				.withTarget(playRandonRoundLink(dto))
				.toLink();
	}

	private Link playRandonRoundLink(GameDTO dto) {
		return linkTo(methodOn(PlayRoundRandomController.class).playRoundRandom(dto.getId(), null))
				.withRel(PLAY_RANDOM_ROUND_AFFORDANCE);
	}
}
