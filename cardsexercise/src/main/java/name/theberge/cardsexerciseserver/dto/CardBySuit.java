package name.theberge.cardsexerciseserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import name.theberge.cardsexerciseserver.model.CardSuit;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardBySuit {
  @Getter
  private CardSuit cardSuit;
  @Getter
  private Integer count;
}
