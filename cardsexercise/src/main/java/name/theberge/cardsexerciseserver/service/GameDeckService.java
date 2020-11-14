package name.theberge.cardsexerciseserver.service;

import name.theberge.cardsexerciseserver.model.Card;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.GameDeck;

public interface GameDeckService {
    void addACardDeck(GameDeck gameDeck, CardDeck cardDeck);

    Card dealAcard(GameDeck gameDeck);

    int getUndealtCardCount();

    int getUndealtCardCountBySuitAndValue();

    void shuffle();

    GameDeck getByGame(Game game);

    void deleteByGame(Game game);
}
