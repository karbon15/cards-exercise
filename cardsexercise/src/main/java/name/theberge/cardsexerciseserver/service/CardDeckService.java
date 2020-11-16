package name.theberge.cardsexerciseserver.service;

import java.util.UUID;
import name.theberge.cardsexerciseserver.model.CardDeck;

public interface CardDeckService {
  CardDeck create();

  CardDeck update(CardDeck cardDeck);

  CardDeck getById(UUID deckId);
}
