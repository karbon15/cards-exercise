package name.theberge.cardsexerciseserver.unit.service;

import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.unit.repository.GameRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class GameServiceImplTests {
    @MockBean
    GameRepository gameRepository;

    @MockBean
    GameDeckService gameDeckService;

    @Autowired
    GameService gameService;
;

    @Test
    @DisplayName("Creating a game should generate an id and call the repository")
    void creatingAGame() {
        Mockito.when(gameRepository.create(Mockito.any(Game.class)))
            .then(AdditionalAnswers.returnsFirstArg());

        gameService.create();

        Mockito.verify(gameRepository).create(ArgumentMatchers.argThat(game -> game.getId() != null));
    }

    @Test
    @DisplayName("Deleting a game should delete the game and cascade to the game deck service")
    void deletingAGame() {
        Game theGame = new Game();

        gameService.delete(theGame.getId());

        Mockito.verify(gameRepository).delete(ArgumentMatchers.argThat(id -> id.equals(theGame.getId())));
        Mockito.verify(gameDeckService).deleteByGameId(ArgumentMatchers.argThat(id -> id.equals(theGame.getId())));
    }
}
