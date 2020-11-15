package name.theberge.cardsexerciseserver.formatter;

import name.theberge.cardsexerciseserver.dto.GetGamePlayersResponse;
import name.theberge.cardsexerciseserver.model.Card;
import name.theberge.cardsexerciseserver.model.CardFaceValue;
import name.theberge.cardsexerciseserver.model.CardSuite;
import name.theberge.cardsexerciseserver.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GamePlayersResponseFormatterTests {

    @Test
    @DisplayName("Formatting a players list")
    public void testFormattingOfAPlayerList() {
        Player playerA = new Player();
        Player playerB = new Player();

        playerA.receiveCards(List.of(
                new Card(CardSuite.CLUBS, CardFaceValue.TEN),
                new Card(CardSuite.HEARTS, CardFaceValue.K)
        ));

        playerB.receiveCards(List.of(
                new Card(CardSuite.CLUBS, CardFaceValue.SEVEN),
                new Card(CardSuite.HEARTS, CardFaceValue.Q)
        ));

        GetGamePlayersResponse response = GamePlayersResponseFormatter.toGamePlayersResponse(List.of(playerA, playerB));

        Assertions.assertEquals(23, response.getPlayers().get(0).getScore());
        Assertions.assertEquals(19, response.getPlayers().get(1).getScore());

        Assertions.assertEquals(playerA.getId(), response.getPlayers().get(0).getId());
        Assertions.assertEquals(playerB.getId(), response.getPlayers().get(1).getId());
    }
}
