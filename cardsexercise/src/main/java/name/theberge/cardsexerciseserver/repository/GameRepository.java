package name.theberge.cardsexerciseserver.repository;

import name.theberge.cardsexerciseserver.model.Game;

public interface GameRepository {
    Game create(Game game);
    void delete(Game game);
    Game get(Game game);
    Game update(Game game);
}
