package name.theberge.cardsexerciseserver.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import name.theberge.cardsexerciseserver.model.CardDeck;

@AllArgsConstructor
@NoArgsConstructor
public class CreateDeckResponse {
  @Getter
  private UUID id;

  public CreateDeckResponse(CardDeck deck) {
    id = deck.getId();
  }

}
