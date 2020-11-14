package name.theberge.cardsexerciseserver.repository;

import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.GameDeck;

public interface GameDeckRepository {
    GameDeck create(GameDeck gameDeck);
    GameDeck update(GameDeck gameDeck);
    GameDeck getByGame(Game game);

}
