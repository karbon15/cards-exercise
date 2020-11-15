package name.theberge.cardsexerciseserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Card {
    @Getter
    private CardSuit suit;
    @Getter
    private CardFaceValue faceValue;
}
