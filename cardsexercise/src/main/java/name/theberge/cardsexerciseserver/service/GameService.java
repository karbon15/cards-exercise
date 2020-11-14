package name.theberge.cardsexerciseserver.service;

import name.theberge.cardsexerciseserver.model.*;

import java.util.Collection;

public interface GameService {
    Game create();

    void delete(Game game);

    void addPlayer(Game game, Player player);

    void removePlayer(Game game, Player player);

    Collection<Card> getCardsForPlayer(Game game, Player player);

    Collection<Player> getPlayers(Game game);

    void addACardDeck(Game game, CardDeck cardDeck);

    Card dealACard(Game game, Player player);

    int getUndealtCardCount(Game game);

    int getUndealtCardCountBySuitAndValue(Game game);

    void shuffle(Game game);
}
