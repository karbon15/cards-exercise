package name.theberge.cardsexerciseserver.service;

import name.theberge.cardsexerciseserver.model.*;
import org.javatuples.Pair;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public interface GameService {
    Game create();

    void delete(UUID gameId);

    Player createPlayer(UUID gameId);

    void removePlayer(UUID gameId, UUID playerId);

    Collection<Card> getCardsForPlayer(UUID gameId, UUID playerId);

    Collection<Player> getPlayers(UUID gameId);

    void addACardDeck(UUID gameId, UUID deckId);

    Collection<Card> dealCards(UUID gameId, UUID playerId, int howMany);

    Map<CardSuit, Integer> getUndealtCardCountBySuit(UUID gameId);

    Map<Pair<CardSuit, CardFaceValue>, Integer>  getUndealtCardCountBySuitAndValue(UUID gameId);

    void shuffle(UUID gameId);
}
