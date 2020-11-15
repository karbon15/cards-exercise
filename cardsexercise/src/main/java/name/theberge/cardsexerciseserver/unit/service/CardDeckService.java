package name.theberge.cardsexerciseserver.unit.service;

import name.theberge.cardsexerciseserver.model.CardDeck;

import java.util.UUID;

public interface CardDeckService {
    CardDeck create();
    CardDeck update(CardDeck cardDeck);
    CardDeck getById(UUID deckId);
}
