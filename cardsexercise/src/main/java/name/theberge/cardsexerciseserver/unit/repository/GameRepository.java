package name.theberge.cardsexerciseserver.unit.repository;

import name.theberge.cardsexerciseserver.model.Game;

import java.util.UUID;

public interface GameRepository {
    Game create(Game game);
    void delete(UUID gameId);
    Game get(UUID gameId);
    Game update(Game game);
}
