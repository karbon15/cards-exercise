package name.theberge.cardsexerciseserver.unit.service;

import name.theberge.cardsexerciseserver.model.*;

import java.util.Collection;
import java.util.UUID;

public interface GameService {
    Game create();

    void delete(UUID gameId);

    void addPlayer(Game game, Player player);

    void removePlayer(Game game, Player player);

    Collection<Card> getCardsForPlayer(Game game, Player player);

    Collection<Player> getPlayers(Game game);

    void addACardDeck(UUID gameId, UUID deckId);

    Card dealCards(Game game, Player player, int howMany);

    int getUndealtCardCount(Game game);

    int getUndealtCardCountBySuitAndValue(Game game);

    void shuffle(Game game);
}
