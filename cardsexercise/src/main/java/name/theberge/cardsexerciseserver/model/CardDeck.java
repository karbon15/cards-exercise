package name.theberge.cardsexerciseserver.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class CardDeck {
    private static List<Card> aNewDeck = List.of(
        new Card(CardSuite.CLUBS, CardFaceValue.TWO), 
        new Card(CardSuite.CLUBS, CardFaceValue.THREE),
        new Card(CardSuite.CLUBS, CardFaceValue.FOUR),
        new Card(CardSuite.CLUBS, CardFaceValue.FIVE),
        new Card(CardSuite.CLUBS, CardFaceValue.SIX),
        new Card(CardSuite.CLUBS, CardFaceValue.SEVEN),
        new Card(CardSuite.CLUBS, CardFaceValue.EIGHT),
        new Card(CardSuite.CLUBS, CardFaceValue.NINE),
        new Card(CardSuite.CLUBS, CardFaceValue.TEN),
        new Card(CardSuite.CLUBS, CardFaceValue.J),
        new Card(CardSuite.CLUBS, CardFaceValue.Q),
        new Card(CardSuite.CLUBS, CardFaceValue.K),
        new Card(CardSuite.CLUBS, CardFaceValue.A),
        new Card(CardSuite.DIAMONDS, CardFaceValue.TWO),
        new Card(CardSuite.DIAMONDS, CardFaceValue.THREE),
        new Card(CardSuite.DIAMONDS, CardFaceValue.FOUR),
        new Card(CardSuite.DIAMONDS, CardFaceValue.FIVE),
        new Card(CardSuite.DIAMONDS, CardFaceValue.SIX),
        new Card(CardSuite.DIAMONDS, CardFaceValue.SEVEN),
        new Card(CardSuite.DIAMONDS, CardFaceValue.EIGHT),
        new Card(CardSuite.DIAMONDS, CardFaceValue.NINE),
        new Card(CardSuite.DIAMONDS, CardFaceValue.TEN),
        new Card(CardSuite.DIAMONDS, CardFaceValue.J),
        new Card(CardSuite.DIAMONDS, CardFaceValue.Q),
        new Card(CardSuite.DIAMONDS, CardFaceValue.K),
        new Card(CardSuite.DIAMONDS, CardFaceValue.A),
        new Card(CardSuite.SPADES, CardFaceValue.TWO),
        new Card(CardSuite.SPADES, CardFaceValue.THREE),
        new Card(CardSuite.SPADES, CardFaceValue.FOUR),
        new Card(CardSuite.SPADES, CardFaceValue.FIVE),
        new Card(CardSuite.SPADES, CardFaceValue.SIX),
        new Card(CardSuite.SPADES, CardFaceValue.SEVEN),
        new Card(CardSuite.SPADES, CardFaceValue.EIGHT),
        new Card(CardSuite.SPADES, CardFaceValue.NINE),
        new Card(CardSuite.SPADES, CardFaceValue.TEN),
        new Card(CardSuite.SPADES, CardFaceValue.J),
        new Card(CardSuite.SPADES, CardFaceValue.Q),
        new Card(CardSuite.SPADES, CardFaceValue.K),
        new Card(CardSuite.SPADES, CardFaceValue.A),
        new Card(CardSuite.HEARTS, CardFaceValue.TWO),
        new Card(CardSuite.HEARTS, CardFaceValue.THREE),
        new Card(CardSuite.HEARTS, CardFaceValue.FOUR),
        new Card(CardSuite.HEARTS, CardFaceValue.FIVE),
        new Card(CardSuite.HEARTS, CardFaceValue.SIX),
        new Card(CardSuite.HEARTS, CardFaceValue.SEVEN),
        new Card(CardSuite.HEARTS, CardFaceValue.EIGHT),
        new Card(CardSuite.HEARTS, CardFaceValue.NINE),
        new Card(CardSuite.HEARTS, CardFaceValue.TEN),
        new Card(CardSuite.HEARTS, CardFaceValue.J),
        new Card(CardSuite.HEARTS, CardFaceValue.Q),
        new Card(CardSuite.HEARTS, CardFaceValue.K),
        new Card(CardSuite.HEARTS, CardFaceValue.A)
    );

    protected LinkedList<Card> myCards;

    @Getter
    private UUID id;

    @Getter
    @Setter
    private Boolean isUsed = false;

    public CardDeck() {
        id = UUID.randomUUID();
        myCards = new LinkedList(aNewDeck);
    }

    public CardDeck(CardDeck cd) {
        id = cd.id;
        myCards = new LinkedList(cd.myCards);
        isUsed = cd.isUsed;
    }

    public void shuffle() {
        // TODO: Explicit ask not to use this
        Collections.shuffle(myCards);
    }
}
