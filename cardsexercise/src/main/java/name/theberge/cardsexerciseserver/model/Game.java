package name.theberge.cardsexerciseserver.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Game {

    @Getter
    private UUID id;

    @Getter
    private Collection<Player> players = new ArrayList<>();

    @Getter
    private GameDeck gameDeck = new GameDeck();

    public Game() {
        id = UUID.randomUUID();
    }

    public Game(Game game) {
        this.id = game.id;
        this.players = game.players;
        this.gameDeck = game.gameDeck;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        //TODO: Maybe throw if the player didnt exist
        players.remove(player);
    }
}
