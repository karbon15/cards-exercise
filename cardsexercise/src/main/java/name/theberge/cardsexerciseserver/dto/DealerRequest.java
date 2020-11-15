package name.theberge.cardsexerciseserver.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DealerRequest {

  @Getter
  private UUID playerId;
  @Getter
  private int count;
}
