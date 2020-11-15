package name.theberge.cardsexerciseserver.model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class CardDeck {
    private static final int SHUFFLE_ROUNDS_BY_CARD = 20;

    private static List<Card> aNewDeck = List.of(
        new Card(CardSuit.CLUBS, CardFaceValue.TWO),
        new Card(CardSuit.CLUBS, CardFaceValue.THREE),
        new Card(CardSuit.CLUBS, CardFaceValue.FOUR),
        new Card(CardSuit.CLUBS, CardFaceValue.FIVE),
        new Card(CardSuit.CLUBS, CardFaceValue.SIX),
        new Card(CardSuit.CLUBS, CardFaceValue.SEVEN),
        new Card(CardSuit.CLUBS, CardFaceValue.EIGHT),
        new Card(CardSuit.CLUBS, CardFaceValue.NINE),
        new Card(CardSuit.CLUBS, CardFaceValue.TEN),
        new Card(CardSuit.CLUBS, CardFaceValue.J),
        new Card(CardSuit.CLUBS, CardFaceValue.Q),
        new Card(CardSuit.CLUBS, CardFaceValue.K),
        new Card(CardSuit.CLUBS, CardFaceValue.A),
        new Card(CardSuit.DIAMONDS, CardFaceValue.TWO),
        new Card(CardSuit.DIAMONDS, CardFaceValue.THREE),
        new Card(CardSuit.DIAMONDS, CardFaceValue.FOUR),
        new Card(CardSuit.DIAMONDS, CardFaceValue.FIVE),
        new Card(CardSuit.DIAMONDS, CardFaceValue.SIX),
        new Card(CardSuit.DIAMONDS, CardFaceValue.SEVEN),
        new Card(CardSuit.DIAMONDS, CardFaceValue.EIGHT),
        new Card(CardSuit.DIAMONDS, CardFaceValue.NINE),
        new Card(CardSuit.DIAMONDS, CardFaceValue.TEN),
        new Card(CardSuit.DIAMONDS, CardFaceValue.J),
        new Card(CardSuit.DIAMONDS, CardFaceValue.Q),
        new Card(CardSuit.DIAMONDS, CardFaceValue.K),
        new Card(CardSuit.DIAMONDS, CardFaceValue.A),
        new Card(CardSuit.SPADES, CardFaceValue.TWO),
        new Card(CardSuit.SPADES, CardFaceValue.THREE),
        new Card(CardSuit.SPADES, CardFaceValue.FOUR),
        new Card(CardSuit.SPADES, CardFaceValue.FIVE),
        new Card(CardSuit.SPADES, CardFaceValue.SIX),
        new Card(CardSuit.SPADES, CardFaceValue.SEVEN),
        new Card(CardSuit.SPADES, CardFaceValue.EIGHT),
        new Card(CardSuit.SPADES, CardFaceValue.NINE),
        new Card(CardSuit.SPADES, CardFaceValue.TEN),
        new Card(CardSuit.SPADES, CardFaceValue.J),
        new Card(CardSuit.SPADES, CardFaceValue.Q),
        new Card(CardSuit.SPADES, CardFaceValue.K),
        new Card(CardSuit.SPADES, CardFaceValue.A),
        new Card(CardSuit.HEARTS, CardFaceValue.TWO),
        new Card(CardSuit.HEARTS, CardFaceValue.THREE),
        new Card(CardSuit.HEARTS, CardFaceValue.FOUR),
        new Card(CardSuit.HEARTS, CardFaceValue.FIVE),
        new Card(CardSuit.HEARTS, CardFaceValue.SIX),
        new Card(CardSuit.HEARTS, CardFaceValue.SEVEN),
        new Card(CardSuit.HEARTS, CardFaceValue.EIGHT),
        new Card(CardSuit.HEARTS, CardFaceValue.NINE),
        new Card(CardSuit.HEARTS, CardFaceValue.TEN),
        new Card(CardSuit.HEARTS, CardFaceValue.J),
        new Card(CardSuit.HEARTS, CardFaceValue.Q),
        new Card(CardSuit.HEARTS, CardFaceValue.K),
        new Card(CardSuit.HEARTS, CardFaceValue.A)
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
        Random random = new Random();
        int deckSize = myCards.size();
        for (int i = 0; i < SHUFFLE_ROUNDS_BY_CARD * deckSize; i++) {
            Card c = myCards.pop();
            int nextPosition = random.nextInt(myCards.size() -1);
            myCards.add(nextPosition, c);
        }
    }
}
