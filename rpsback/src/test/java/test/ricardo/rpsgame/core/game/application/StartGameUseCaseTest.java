package test.ricardo.rpsgame.core.game.application;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import test.ricardo.rpsgame.core.game.domain.Game;
import test.ricardo.rpsgame.core.game.domain.Game.Status;
import test.ricardo.rpsgame.core.game.domain.GameRepository;

@ExtendWith(MockitoExtension.class)
class StartGameUseCaseTest {

	@Mock
	private GameRepository gameRepository;

	@InjectMocks
	private StartGameUseCase useCase;

	@Test
	void testStartGame() {
		when(gameRepository.save(any())).thenAnswer(returnsFirstArg());

		Game game = useCase.start();

		Assertions.assertNotNull(game);
		Assertions.assertEquals(Status.ONGOING, game.getStatus());
	}
	
}
