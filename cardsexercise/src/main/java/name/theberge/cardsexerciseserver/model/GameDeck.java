package name.theberge.cardsexerciseserver.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

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

  public Collection<Card> dealCards(int howMany) {
    List listOfCards = myCards.subList(0, howMany);
    LinkedList<Card> toReturn = new LinkedList<>(listOfCards);

    listOfCards.clear();
    return toReturn;
  }

}
