package name.theberge.cardsexerciseserver.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class DeckAssignmentRequest {

  @Getter
  @Setter
  private UUID deckId;
}
