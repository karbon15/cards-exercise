package name.theberge.cardsexerciseserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Card {
    @Getter
    private CardSuite suite;
    @Getter
    private CardFaceValue faceValue;
}
