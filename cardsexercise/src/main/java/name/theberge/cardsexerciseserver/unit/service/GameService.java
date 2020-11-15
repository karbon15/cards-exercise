package name.theberge.cardsexerciseserver.unit.service;

import name.theberge.cardsexerciseserver.model.*;

import java.util.Collection;
import java.util.UUID;

public interface GameService {
    Game create();

    void delete(UUID gameId);

    Player createPlayer(UUID gameId);

    void removePlayer(UUID gameId, UUID playerId);

    Collection<Card> getCardsForPlayer(Game game, Player player);

    Collection<Player> getPlayers(Game game);

    void addACardDeck(UUID gameId, UUID deckId);

    Collection<Card> dealCards(UUID gameId, UUID playerId, int howMany);

    int getUndealtCardCount(Game game);

    int getUndealtCardCountBySuitAndValue(Game game);

    void shuffle(Game game);
}
