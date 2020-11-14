package name.theberge.cardsexerciseserver.unit.service;

import name.theberge.cardsexerciseserver.model.CardDeck;

public interface CardDeckService {
    CardDeck create();
    CardDeck update(CardDeck cardDeck);
}
