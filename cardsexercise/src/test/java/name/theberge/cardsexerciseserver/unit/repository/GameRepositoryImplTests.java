package name.theberge.cardsexerciseserver.unit.repository;

import name.theberge.cardsexerciseserver.exception.GameAlreadyExistsException;
import name.theberge.cardsexerciseserver.exception.GameNotFoundException;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GameRepositoryImplTests {

    @Autowired
    GameRepository gameRepository;

    @Test
    @DisplayName("Creating a game should allow reading back that same game")
    void creatingAGame() {
        Game game = new Game();

        gameRepository.create(game);
        Game readGame = gameRepository.get(game.getId());

        Assertions.assertEquals(game.getId(), readGame.getId());
    }

    @Test
    @DisplayName("Creating a game that already exists")
    void creatingAGameAlreadyExisting() {
        Game game = new Game();

        gameRepository.create(game);
        Assertions.assertThrows(GameAlreadyExistsException.class, () -> gameRepository.create(game));
    }

    @Test
    @DisplayName("Deleting a game should make it unnaccessible")
    void deletingAGame() {
        Game game = new Game();

        gameRepository.create(game);
        gameRepository.delete(game.getId());

        Assertions.assertThrows(GameNotFoundException.class, () -> gameRepository.get(game.getId()));
    }

    @Test
    @DisplayName("An updated game should have the updated properties when read back")
    void updatingAGame() {
        Game game = new Game();

        gameRepository.create(game);

        //Simulate that we persisted in a database by cloning
        Game updatedGame = new Game(game);
        updatedGame.addPlayer(new Player());
        gameRepository.update(updatedGame);
        Game readGame = gameRepository.get(game.getId());
        Assertions.assertEquals(game.getPlayers(), readGame.getPlayers());
    }

    @Test
    @DisplayName("Updating a game that doesnt exist should make it unnaccessible")
    void updatingAGameNotFound() {
        Game game = new Game();
        Assertions.assertThrows(GameNotFoundException.class, () -> gameRepository.update(game));
    }
}
