package name.theberge.cardsexerciseserver.repository;

import java.util.UUID;
import name.theberge.cardsexerciseserver.model.Game;

public interface GameRepository {
  Game create(Game game);

  void delete(UUID gameId);

  Game get(UUID gameId);

  Game update(Game game);
}
