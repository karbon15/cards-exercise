package name.theberge.cardsexerciseserver.formatter;

import name.theberge.cardsexerciseserver.dto.GetCardsBySuitResponse;
import name.theberge.cardsexerciseserver.dto.GetGamePlayersResponse;
import name.theberge.cardsexerciseserver.model.Card;
import name.theberge.cardsexerciseserver.model.CardFaceValue;
import name.theberge.cardsexerciseserver.model.CardSuite;
import name.theberge.cardsexerciseserver.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetCardsBySuiteFormatterTests {

    @Test
    @DisplayName("Formatting a structure of card suites")
    public void testFormattingCardsBySuit() {

        Map<CardSuite, Integer> input = new HashMap();
        input.put(CardSuite.HEARTS, 123);
        input.put(CardSuite.SPADES, 12);
        input.put(CardSuite.CLUBS, 13);
        input.put(CardSuite.DIAMONDS, 124);

        GetCardsBySuitResponse response = GetCardsBySuitFormatter.toGetCardsBySuitResponseFormatter(input);

        Assertions.assertEquals(4, response.getCardsBySuit().size());

        Assertions.assertEquals(CardSuite.DIAMONDS, response.getCardsBySuit().get(0).getCardSuite());
        Assertions.assertEquals(CardSuite.CLUBS, response.getCardsBySuit().get(1).getCardSuite());
        Assertions.assertEquals(CardSuite.SPADES, response.getCardsBySuit().get(2).getCardSuite());
        Assertions.assertEquals(CardSuite.HEARTS, response.getCardsBySuit().get(3).getCardSuite());

        Assertions.assertEquals(124, response.getCardsBySuit().get(0).getCount());
        Assertions.assertEquals(13, response.getCardsBySuit().get(1).getCount());
        Assertions.assertEquals(12, response.getCardsBySuit().get(2).getCount());
        Assertions.assertEquals(123, response.getCardsBySuit().get(3).getCount());
    }

    @Test
    @DisplayName("Formatting a structure of card suites (incomplete)")
    public void testFormattingCardsBySuitIncomplete() {

        Map<CardSuite, Integer> input = new HashMap();
        input.put(CardSuite.SPADES, 12);
        input.put(CardSuite.CLUBS, 13);
        input.put(CardSuite.DIAMONDS, 124);

        GetCardsBySuitResponse response = GetCardsBySuitFormatter.toGetCardsBySuitResponseFormatter(input);

        Assertions.assertEquals(3, response.getCardsBySuit().size());

        Assertions.assertEquals(CardSuite.DIAMONDS, response.getCardsBySuit().get(0).getCardSuite());
        Assertions.assertEquals(CardSuite.CLUBS, response.getCardsBySuit().get(1).getCardSuite());
        Assertions.assertEquals(CardSuite.SPADES, response.getCardsBySuit().get(2).getCardSuite());

        Assertions.assertEquals(124, response.getCardsBySuit().get(0).getCount());
        Assertions.assertEquals(13, response.getCardsBySuit().get(1).getCount());
        Assertions.assertEquals(12, response.getCardsBySuit().get(2).getCount());
    }
}
