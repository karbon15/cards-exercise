package name.theberge.cardsexerciseserver.formatter;

import name.theberge.cardsexerciseserver.dto.GetCardsBySuitAndValueResponse;
import name.theberge.cardsexerciseserver.model.CardFaceValue;
import name.theberge.cardsexerciseserver.model.CardSuit;
import org.javatuples.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class GetCardsBySuitAndValueFormatterTests {

    @Test
    @DisplayName("Formatting a structure of card suits")
    public void testFormattingCardsBySuitAndValue() {

        Map<Pair<CardSuit, CardFaceValue>, Integer> input = new HashMap();
        input.put(Pair.with(CardSuit.HEARTS, CardFaceValue.TWO), 12);
        input.put(Pair.with(CardSuit.HEARTS, CardFaceValue.A), 125);
        input.put(Pair.with(CardSuit.DIAMONDS, CardFaceValue.TWO), 123);
        input.put(Pair.with(CardSuit.CLUBS, CardFaceValue.K), 125);

        GetCardsBySuitAndValueResponse response =
                GetCardsBySuitAndValueFormatter.toGetCardsBySuitAndValueResponseFormatter(input);

        Assertions.assertEquals(4, response.getCardsBySuitAndValue().size());

        Assertions.assertEquals(Pair.with(CardSuit.DIAMONDS, CardFaceValue.TWO),
                response.getCardsBySuitAndValue().get(0).getCardSuitAndValue());
        Assertions.assertEquals(Pair.with(CardSuit.CLUBS, CardFaceValue.K),
                response.getCardsBySuitAndValue().get(1).getCardSuitAndValue());
        Assertions.assertEquals(Pair.with(CardSuit.HEARTS, CardFaceValue.A),
                response.getCardsBySuitAndValue().get(2).getCardSuitAndValue());
        Assertions.assertEquals(Pair.with(CardSuit.HEARTS, CardFaceValue.TWO),
                response.getCardsBySuitAndValue().get(3).getCardSuitAndValue());

        Assertions.assertEquals(123, response.getCardsBySuitAndValue().get(0).getCount());
        Assertions.assertEquals(125, response.getCardsBySuitAndValue().get(1).getCount());
        Assertions.assertEquals(125, response.getCardsBySuitAndValue().get(2).getCount());
        Assertions.assertEquals(12, response.getCardsBySuitAndValue().get(3).getCount());

    }
}
