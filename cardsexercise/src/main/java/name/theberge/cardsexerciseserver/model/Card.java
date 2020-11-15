package name.theberge.cardsexerciseserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Card {
  @Getter
  private final CardSuit suit;
  @Getter
  private final CardFaceValue faceValue;
}
