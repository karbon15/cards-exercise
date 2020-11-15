package name.theberge.cardsexerciseserver.unit.repository;

import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.GameDeck;

import java.util.UUID;

public interface GameDeckRepository {
    GameDeck create(GameDeck gameDeck);
    GameDeck update(GameDeck gameDeck);
    GameDeck getByGame(UUID gameId);

}
