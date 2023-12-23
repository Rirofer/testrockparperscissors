package test.ricardo.rpsgame.adapters.game.in.http;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import test.ricardo.rpsgame.adapters.game.in.http.dto.PlayRoundRandomCommandDTO;
import test.ricardo.rpsgame.adapters.game.in.http.dto.PlayRoundRandomCommandDTOMother;
import test.ricardo.rpsgame.adapters.shared.in.http.JsonSerializer;
import test.ricardo.rpsgame.adapters.shared.in.http.MockMvcDocumentationTestBase;
import test.ricardo.rpsgame.core.game.domain.Game;
import test.ricardo.rpsgame.core.game.domain.GameMother;
import test.ricardo.rpsgame.core.game.domain.GameRepository;
import test.ricardo.rpsgame.core.game.domain.Move;

class PlayRoundRandomUseCaseComponentTest extends MockMvcDocumentationTestBase {

	@Autowired
	private GameRepository gameRepository;

	@AfterEach
	void tearDown() {
		gameRepository.deleteAll();
	}

	@Test
	void testPlayRoundRandomOngoingGame() throws Exception {
		Game game = GameMother.create();
		gameRepository.save(game);
		PlayRoundRandomCommandDTO commandDTO = PlayRoundRandomCommandDTOMother.create(Move.PAPER);

		mockMvc.perform(post(ApiPaths.GAMES + "/" + game.getId() + ApiPaths.PLAY_ROUND_RANDOM)
				.content(JsonSerializer.toJson(commandDTO))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.rounds", hasSize(1)))
				.andDo(document("play-random-round"));
	}

	@Test
	void testPlayRoundRandomFinishedGameShouldReturnConflict() throws Exception {
		Game game = GameMother.createFinished();
		gameRepository.save(game);
		PlayRoundRandomCommandDTO commandDTO = PlayRoundRandomCommandDTOMother.create(Move.PAPER);

		mockMvc.perform(post(ApiPaths.GAMES + "/" + game.getId() + ApiPaths.PLAY_ROUND_RANDOM)
				.content(JsonSerializer.toJson(commandDTO))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isConflict());
	}

	@Test
	void testPlayRoundRandomWithNullMoveShouldReturnBadRequest() throws Exception {
		Game game = GameMother.create();
		gameRepository.save(game);
		PlayRoundRandomCommandDTO commandDTO = PlayRoundRandomCommandDTOMother.create(null);

		mockMvc.perform(post(ApiPaths.GAMES + "/" + game.getId() + ApiPaths.PLAY_ROUND_RANDOM)
				.content(JsonSerializer.toJson(commandDTO))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}
}
