package name.theberge.cardsexerciseserver.unit.service;

import name.theberge.cardsexerciseserver.model.Card;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.GameDeck;

import java.util.UUID;

public interface GameDeckService {
    void addACardDeck(GameDeck gameDeck, CardDeck cardDeck);

    Card dealAcard(GameDeck gameDeck);

    int getUndealtCardCount();

    int getUndealtCardCountBySuitAndValue();

    void shuffle();

    GameDeck getByGameId(UUID gameId);

    void deleteByGameId(UUID gameId);
}
