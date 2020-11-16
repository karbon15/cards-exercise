package name.theberge.cardsexerciseserver.formatter;

import name.theberge.cardsexerciseserver.dto.GetCardsBySuitResponse;
import name.theberge.cardsexerciseserver.model.CardSuit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class GetCardsBySuitFormatterTests {

  @Test
  @DisplayName("Formatting a structure of card suits")
  public void testFormattingCardsBySuit() {

    Map<CardSuit, Integer> input = new HashMap();
    input.put(CardSuit.HEARTS, 123);
    input.put(CardSuit.SPADES, 12);
    input.put(CardSuit.CLUBS, 13);
    input.put(CardSuit.DIAMONDS, 124);

    GetCardsBySuitResponse response =
        GetCardsBySuitFormatter.toGetCardsBySuitResponseFormatter(input);

    Assertions.assertEquals(4, response.getCardsBySuit().size());

    Assertions.assertEquals(CardSuit.DIAMONDS, response.getCardsBySuit().get(0).getCardSuit());
    Assertions.assertEquals(CardSuit.CLUBS, response.getCardsBySuit().get(1).getCardSuit());
    Assertions.assertEquals(CardSuit.SPADES, response.getCardsBySuit().get(2).getCardSuit());
    Assertions.assertEquals(CardSuit.HEARTS, response.getCardsBySuit().get(3).getCardSuit());

    Assertions.assertEquals(124, response.getCardsBySuit().get(0).getCount());
    Assertions.assertEquals(13, response.getCardsBySuit().get(1).getCount());
    Assertions.assertEquals(12, response.getCardsBySuit().get(2).getCount());
    Assertions.assertEquals(123, response.getCardsBySuit().get(3).getCount());
  }

  @Test
  @DisplayName("Formatting a structure of card suits (incomplete)")
  public void testFormattingCardsBySuitIncomplete() {

    Map<CardSuit, Integer> input = new HashMap();
    input.put(CardSuit.SPADES, 12);
    input.put(CardSuit.CLUBS, 13);
    input.put(CardSuit.DIAMONDS, 124);

    GetCardsBySuitResponse response =
        GetCardsBySuitFormatter.toGetCardsBySuitResponseFormatter(input);

    Assertions.assertEquals(3, response.getCardsBySuit().size());

    Assertions.assertEquals(CardSuit.DIAMONDS, response.getCardsBySuit().get(0).getCardSuit());
    Assertions.assertEquals(CardSuit.CLUBS, response.getCardsBySuit().get(1).getCardSuit());
    Assertions.assertEquals(CardSuit.SPADES, response.getCardsBySuit().get(2).getCardSuit());

    Assertions.assertEquals(124, response.getCardsBySuit().get(0).getCount());
    Assertions.assertEquals(13, response.getCardsBySuit().get(1).getCount());
    Assertions.assertEquals(12, response.getCardsBySuit().get(2).getCount());
  }
}
