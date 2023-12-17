package test.ricardo.rpsgame.core.game.application;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.anyList;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import test.ricardo.rpsgame.core.event.domain.DomainEventPublisher;
import test.ricardo.rpsgame.core.game.domain.Game;
import test.ricardo.rpsgame.core.game.domain.GameMother;
import test.ricardo.rpsgame.core.game.domain.GameRepository;
import test.ricardo.rpsgame.core.game.domain.PlayRoundRandomCommand;
import test.ricardo.rpsgame.core.game.domain.PlayRoundRandomCommandMother;
import test.ricardo.rpsgame.core.shared.application.NotFoundException;

@ExtendWith(MockitoExtension.class)
class PlayRoundRandomUseCaseTest {

	@Mock
	private GameRepository gameRepository;

	@Mock
	private DomainEventPublisher domainEventPublisher;

	@InjectMocks
	private PlayRoundRandomUseCase useCase;

	@Test
	@DisplayName("givenExistingGame_whenPlayingRandomRound_thenShouldSaveGameAndPublishEvents")
	void testPlayRoundExistingGame() {
		PlayRoundRandomCommand command = PlayRoundRandomCommandMother.create();
		Game game = GameMother.create(command.gameId());
		when(gameRepository.findById(game.getId())).thenReturn(Optional.of(game));
		when(gameRepository.save(game)).thenAnswer(returnsFirstArg());

		Game res = useCase.playRound(command);

		Assertions.assertEquals(game, res);
		verify(domainEventPublisher).publish(anyList());
	}

	@Test
	@DisplayName("givenNotExistingGame_whenPlayingRandomRound_thenShouldThrowNotFoundException")
	void testPlayRoundNotExistingGame() {
		PlayRoundRandomCommand command = PlayRoundRandomCommandMother.create();
		when(gameRepository.findById(command.gameId())).thenReturn(Optional.empty());

		Assertions.assertThrows(NotFoundException.class, () -> useCase.playRound(command));
	}

}
