package name.theberge.cardsexerciseserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import name.theberge.cardsexerciseserver.model.CardFaceValue;
import name.theberge.cardsexerciseserver.model.CardSuit;
import org.javatuples.Pair;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardBySuitAndValue {
    @Getter
    private Pair<CardSuit, CardFaceValue> cardSuitAndValue;
    @Getter
    private Integer count;
}