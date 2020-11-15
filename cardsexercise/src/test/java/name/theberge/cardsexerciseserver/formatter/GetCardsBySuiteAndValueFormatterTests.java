package name.theberge.cardsexerciseserver.formatter;

import name.theberge.cardsexerciseserver.dto.GetCardsBySuitAndValueResponse;
import name.theberge.cardsexerciseserver.model.CardFaceValue;
import name.theberge.cardsexerciseserver.model.CardSuite;
import org.javatuples.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class GetCardsBySuiteAndValueFormatterTests {

    @Test
    @DisplayName("Formatting a structure of card suites")
    public void testFormattingCardsBySuitAndValue() {

        Map<Pair<CardSuite, CardFaceValue>, Integer> input = new HashMap();
        input.put(Pair.with(CardSuite.HEARTS, CardFaceValue.TWO), 12);
        input.put(Pair.with(CardSuite.HEARTS, CardFaceValue.A), 125);
        input.put(Pair.with(CardSuite.DIAMONDS, CardFaceValue.TWO), 123);
        input.put(Pair.with(CardSuite.CLUBS, CardFaceValue.K), 125);

        GetCardsBySuitAndValueResponse response =
                GetCardsBySuitAndValueFormatter.toGetCardsBySuitAndValueResponseFormatter(input);

        Assertions.assertEquals(4, response.getCardsBySuitAndValue().size());

        Assertions.assertEquals(Pair.with(CardSuite.DIAMONDS, CardFaceValue.TWO),
                response.getCardsBySuitAndValue().get(0).getCardSuiteAndValue());
        Assertions.assertEquals(Pair.with(CardSuite.CLUBS, CardFaceValue.K),
                response.getCardsBySuitAndValue().get(1).getCardSuiteAndValue());
        Assertions.assertEquals(Pair.with(CardSuite.HEARTS, CardFaceValue.A),
                response.getCardsBySuitAndValue().get(2).getCardSuiteAndValue());
        Assertions.assertEquals(Pair.with(CardSuite.HEARTS, CardFaceValue.TWO),
                response.getCardsBySuitAndValue().get(3).getCardSuiteAndValue());

        Assertions.assertEquals(123, response.getCardsBySuitAndValue().get(0).getCount());
        Assertions.assertEquals(125, response.getCardsBySuitAndValue().get(1).getCount());
        Assertions.assertEquals(125, response.getCardsBySuitAndValue().get(2).getCount());
        Assertions.assertEquals(12, response.getCardsBySuitAndValue().get(3).getCount());

    }
}
