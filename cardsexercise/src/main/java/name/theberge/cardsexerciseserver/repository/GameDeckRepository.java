package name.theberge.cardsexerciseserver.repository;

import java.util.UUID;
import name.theberge.cardsexerciseserver.model.GameDeck;

public interface GameDeckRepository {
  GameDeck create(GameDeck gameDeck);

  GameDeck update(GameDeck gameDeck);

  GameDeck getByGame(UUID gameId);

  void delete(UUID gameId);

}
