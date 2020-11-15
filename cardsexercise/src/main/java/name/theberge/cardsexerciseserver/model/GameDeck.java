package name.theberge.cardsexerciseserver.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;

public class GameDeck extends CardDeck {

    @Getter
    @Setter
    private UUID gameId;

    public GameDeck() {
        myCards = new LinkedList();
    }

    public GameDeck(GameDeck gameDeck) {
        this.gameId = gameDeck.gameId;
        this.myCards = gameDeck.myCards;
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

}
