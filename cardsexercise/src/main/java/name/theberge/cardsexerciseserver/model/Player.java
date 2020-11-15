package name.theberge.cardsexerciseserver.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

public class Player {

  @Getter
  @Setter
  private UUID id;

  @Getter
  private final Collection<Card> cards = new ArrayList<>();

  public Player() {
    id = UUID.randomUUID();
  }

  public void receiveCards(Collection<Card> cardsToAdd) {
    cards.addAll(cardsToAdd);
  }
}
