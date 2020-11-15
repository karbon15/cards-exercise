package name.theberge.cardsexerciseserver.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Builder
public class PlayerWithScore {
  @Getter
  private final UUID id;
  @Getter
  private final int score;
}
