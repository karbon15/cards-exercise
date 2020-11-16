package name.theberge.cardsexerciseserver.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import name.theberge.cardsexerciseserver.model.Player;

@AllArgsConstructor
@NoArgsConstructor
public class CreatePlayerResponse {
  @Getter
  @Setter
  private UUID id;

  public CreatePlayerResponse(Player player) {
    id = player.getId();
  }
}
