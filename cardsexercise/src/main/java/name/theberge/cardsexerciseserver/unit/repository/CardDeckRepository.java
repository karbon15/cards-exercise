package name.theberge.cardsexerciseserver.unit.repository;

import name.theberge.cardsexerciseserver.model.CardDeck;

public interface CardDeckRepository {
    CardDeck create();
    CardDeck update(CardDeck cardDeck);
}
