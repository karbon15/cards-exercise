package name.theberge.cardsexerciseserver.service.implementation;

import name.theberge.cardsexerciseserver.model.Card;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.service.GameDeckService;
import org.springframework.stereotype.Service;

@Service
public class GameDeckServiceImpl implements GameDeckService {
    @Override
    public void addACardDeck(CardDeck cardDeck) {

    }

    @Override
    public Card dealAcard() {
        return null;
    }

    @Override
    public int getUndealtCardCount() {
        return 0;
    }

    @Override
    public int getUndealtCardCountBySuitAndValue() {
        return 0;
    }

    @Override
    public void shuffle() {

    }
}
