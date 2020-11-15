package name.theberge.cardsexerciseserver.service;

import name.theberge.cardsexerciseserver.model.CardDeck;

import java.util.UUID;

public interface CardDeckService {
    CardDeck create();
    CardDeck update(CardDeck cardDeck);
    CardDeck getById(UUID deckId);
}
