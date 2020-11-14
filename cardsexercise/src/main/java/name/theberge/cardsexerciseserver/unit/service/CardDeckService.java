package name.theberge.cardsexerciseserver.unit.service;

import name.theberge.cardsexerciseserver.model.CardDeck;

public interface CardDeckService {
    CardDeck create();
    void delete(CardDeck cardDeck);
}
