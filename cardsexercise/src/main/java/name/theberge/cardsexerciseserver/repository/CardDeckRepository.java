package name.theberge.cardsexerciseserver.repository;

import java.util.UUID;
import name.theberge.cardsexerciseserver.model.CardDeck;

public interface CardDeckRepository {
  CardDeck create(CardDeck cardDeck);

  CardDeck update(CardDeck cardDeck);

  CardDeck getById(UUID deckId);
}
