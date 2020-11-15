package name.theberge.cardsexerciseserver.repository.implementation;

import name.theberge.cardsexerciseserver.exception.GameAlreadyExistsException;
import name.theberge.cardsexerciseserver.exception.GameNotFoundException;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.repository.GameRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class GameRepositoryImpl implements GameRepository {

    Map<UUID, Game> games = new HashMap<>();

    @Override
    public Game create(Game game) {
        if (games.containsKey(game.getId())) {
            throw new GameAlreadyExistsException();
        }
        games.put(game.getId(), game);
        return game;
    }

    @Override
    public void delete(UUID gameId) {
        if (!games.containsKey(gameId)) {
           throw new GameNotFoundException();
        }
        games.remove(gameId);

    }

    @Override
    public Game get(UUID gameId) {
        if (!games.containsKey(gameId)) {
            throw new GameNotFoundException();
        }
        return games.get(gameId);
    }

    @Override
    public Game update(Game game) {
        if (!games.containsKey(game.getId())) {
            throw new GameNotFoundException();
        }
        return games.put(game.getId(), game);
    }
}
