package name.theberge.cardsexerciseserver.unit.repository;

import name.theberge.cardsexerciseserver.model.CardDeck;

import java.util.UUID;

public interface CardDeckRepository {
    CardDeck create(CardDeck cardDeck);
    CardDeck update(CardDeck cardDeck);
    CardDeck getById(UUID deckId);
}
