package name.theberge.cardsexerciseserver.dto;

import java.util.Collection;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.Player;

@AllArgsConstructor
public class CreateGameResponse {
  @Getter
  private final UUID id;
  @Getter
  private final Collection<Player> players;

  public CreateGameResponse(Game game) {
    id = game.getId();
    players = game.getPlayers();
  }

}
