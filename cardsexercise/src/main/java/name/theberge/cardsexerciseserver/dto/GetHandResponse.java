package name.theberge.cardsexerciseserver.dto;

import java.util.Collection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import name.theberge.cardsexerciseserver.model.Card;

@NoArgsConstructor
public class GetHandResponse {

  @Getter
  private Collection<Card> cards;

  public GetHandResponse(Collection<Card> inputCards) {
    cards = inputCards;
  }

}
