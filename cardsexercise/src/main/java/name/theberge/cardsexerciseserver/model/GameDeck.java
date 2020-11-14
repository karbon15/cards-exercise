package name.theberge.cardsexerciseserver.model;

import java.util.Collection;
import java.util.LinkedList;

public class GameDeck extends CardDeck {

    public GameDeck() {
        myCards = new LinkedList();
    }

    public void addADeck(CardDeck deck) {
        myCards.addAll(deck.myCards);
    }

    public Collection<Card> getUndealtCards() {
        return myCards;
    }

    public Card dealACard() {
        return myCards.pop();
    }

    public void shuffle() {
        throw new UnsupportedOperationException("Shuffle not supported");
    }
}
