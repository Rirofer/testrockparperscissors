package test.ricardo.rpsgame.adapters.game.in.http;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.halLinks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.hypermedia.LinksSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;

import test.ricardo.rpsgame.adapters.shared.in.http.MockMvcDocumentationTestBase;
import test.ricardo.rpsgame.core.game.domain.Game;
import test.ricardo.rpsgame.core.game.domain.GameMother;
import test.ricardo.rpsgame.core.game.domain.GameRepository;
import test.ricardo.rpsgame.core.game.domain.Round;
import test.ricardo.rpsgame.core.game.domain.RoundMother;
import test.ricardo.rpsgame.core.game.domain.Winner;

class FindGameByIdUseCaseComponentTest extends MockMvcDocumentationTestBase {

	@Autowired
	private GameRepository gameRepository;

	@AfterEach
	void tearDown() {
		gameRepository.deleteAll();
	}

	@Test
	@DisplayName("givenExistingGame_whenFindingById_thenShouldReturnGameResource")
	void testFindById() throws Exception {
		List<Round> rounds = new ArrayList<>(List.of(RoundMother.createTie()));
		Game game = GameMother.createWithRounds(rounds);
		gameRepository.save(game);

		mockMvc.perform(get(ApiPaths.GAMES + "/" + game.getId()))
				.andDo(print())
				.andDo(document("find-game-by-id", gameResponseFields(), roundResponseFields(), documentLinks()))
				.andExpect(status().isOk());
	}

	private ResponseFieldsSnippet gameResponseFields() {
		return responseFields(fieldWithPath("id").description("The game id"),
				fieldWithPath("status").description("The game status. [" + Game.Status.toStringValues() + "]"),
				fieldWithPath("winner").description("The winner. [" + Winner.toStringValues() + "]"),
				fieldWithPath("startedOn").description("The date the game was started"),
				subsectionWithPath("rounds").description("The rounds already played"),
				subsectionWithPath("_links").description("Resource links"),
				subsectionWithPath("_templates").description("Actions that can be executed on the resource"));
	}

	private ResponseFieldsSnippet roundResponseFields() {
		return responseFields(beneathPath("rounds").withSubsectionId("rounds"),
				fieldWithPath("playerOneMove").description("The player one move"),
				fieldWithPath("playerTwoMove").description("The player two move"),
				fieldWithPath("randomPlayerTwo").description("Indicates if player two move was randomly generated"));
	}

	private LinksSnippet documentLinks() {
		return links(halLinks(), linkWithRel("self").description("Self link"));
	}

}
