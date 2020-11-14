package name.theberge.cardsexerciseserver.service;

import name.theberge.cardsexerciseserver.model.Card;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.model.GameDeck;

public interface GameDeckService {
    void addACardDeck(CardDeck cardDeck);

    Card dealAcard();

    int getUndealtCardCount();

    int getUndealtCardCountBySuitAndValue();

    void shuffle();
}
