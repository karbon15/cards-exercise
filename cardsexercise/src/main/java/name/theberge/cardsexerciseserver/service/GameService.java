package name.theberge.cardsexerciseserver.service;

import name.theberge.cardsexerciseserver.model.Card;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.Player;

import java.util.Collection;

public interface GameService {
    void create();

    void delete(Game game);

    void addPlayer(Player player);

    void removePlayer(Player player);

    Collection<Card> getCardsForPlayer(Player player);

    Collection<Player> getPlayers();
}
