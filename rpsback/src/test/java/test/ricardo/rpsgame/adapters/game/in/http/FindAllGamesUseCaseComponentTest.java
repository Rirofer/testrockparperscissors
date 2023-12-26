package test.ricardo.rpsgame.adapters.game.in.http;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.ricardo.rpsgame.adapters.game.in.http.assembler.CollectionGameDTOAssembler;
import test.ricardo.rpsgame.adapters.shared.in.http.MockMvcDocumentationTestBase;
import test.ricardo.rpsgame.core.game.domain.Game;
import test.ricardo.rpsgame.core.game.domain.GameMother;
import test.ricardo.rpsgame.core.game.domain.GameRepository;

class FindAllGamesUseCaseComponentTest extends MockMvcDocumentationTestBase {

	private static final String START_GAME_PATH = "._templates." + CollectionGameDTOAssembler.START_GAME_AFFORDANCE;

	@Autowired
	private GameRepository gameRepository;

	@AfterEach
	void tearDown() {
		gameRepository.deleteAll();
	}

	@Test
	@DisplayName("givenExistingGames_whenFindingById_thenShouldReturnGameCollectionResource")
	void testFindAllGames() throws Exception {
		List<Game> games = givenExistingGames();

		mockMvc.perform(get(ApiPaths.GAMES))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$._embedded." + CollectionGameDTOAssembler.EMBEDDED_REL, hasSize(games.size())))
				.andExpect(jsonPath(START_GAME_PATH).exists())
				.andExpect(jsonPath(START_GAME_PATH + ".target").exists())
				.andExpect(jsonPath(START_GAME_PATH + ".method").value("POST"))
				.andDo(document("find-all-games"));
	}

	private List<Game> givenExistingGames() {
		gameRepository.save(GameMother.create());
		gameRepository.save(GameMother.create());
		gameRepository.save(GameMother.create());
		return gameRepository.findAll();
	}

	@Test
	@DisplayName("givenNoGames_whenFindingById_thenShouldReturnEmptyGameCollectionResource")
	void testFindAllGamesEmpty() throws Exception {
		mockMvc.perform(get(ApiPaths.GAMES))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$._embedded." + CollectionGameDTOAssembler.EMBEDDED_REL, hasSize(0)));
	}

}
