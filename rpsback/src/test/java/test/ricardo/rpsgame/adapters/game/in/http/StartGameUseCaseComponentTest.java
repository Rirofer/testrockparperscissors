package test.ricardo.rpsgame.adapters.game.in.http;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.ricardo.rpsgame.adapters.shared.in.http.MockMvcDocumentationTestBase;
import test.ricardo.rpsgame.core.game.domain.GameRepository;

class StartGameUseCaseComponentTest extends MockMvcDocumentationTestBase {

	@Autowired
	private GameRepository gameRepository;

	@AfterEach
	void tearDown() {
		gameRepository.deleteAll();
	}

	@Test
	void testStartGame() throws Exception {
		mockMvc.perform(post(ApiPaths.START_GAME))
				.andDo(print())
				.andExpect(status().isOk())
				.andDo(document("start-game"));

		Assertions.assertEquals(1, gameRepository.findAll()
				.size());
	}

}
