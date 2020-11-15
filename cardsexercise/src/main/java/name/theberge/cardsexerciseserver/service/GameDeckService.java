package name.theberge.cardsexerciseserver.service;

import name.theberge.cardsexerciseserver.model.Card;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.GameDeck;

import java.util.Collection;
import java.util.UUID;

public interface GameDeckService {

    GameDeck create(UUID gameId);

    void addACardDeck(GameDeck gameDeck, CardDeck cardDeck);

    Collection<Card> dealCards(UUID gameId, int howMany);

    int getUndealtCardCount();

    int getUndealtCardCountBySuitAndValue();

    void shuffle();

    GameDeck getByGameId(UUID gameId);

    void deleteByGameId(UUID gameId);
}
