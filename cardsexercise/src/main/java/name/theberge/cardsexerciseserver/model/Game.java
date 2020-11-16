package name.theberge.cardsexerciseserver.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import lombok.Getter;

public class Game {

  @Getter
  private final UUID id;

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

  public void removePlayer(UUID playerId) {
    players.removeIf(p -> p.getId().equals(playerId));
  }
}
