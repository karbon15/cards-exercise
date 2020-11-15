package name.theberge.cardsexerciseserver.service;

import name.theberge.cardsexerciseserver.model.*;
import org.javatuples.Pair;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public interface GameDeckService {

    GameDeck create(UUID gameId);

    void addACardDeck(GameDeck gameDeck, CardDeck cardDeck);

    Collection<Card> dealCards(UUID gameId, int howMany);

    Map<CardSuite, Integer> getUndealtCardCountBySuit(UUID gameId);

    Map<Pair<CardSuite, CardFaceValue>, Integer>  getUndealtCardCountBySuitAndValue(UUID gameId);

    void shuffle();

    GameDeck getByGameId(UUID gameId);

    void deleteByGameId(UUID gameId);
}
