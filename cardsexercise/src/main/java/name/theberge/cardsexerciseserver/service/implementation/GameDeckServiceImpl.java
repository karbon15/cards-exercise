package name.theberge.cardsexerciseserver.service.implementation;

import name.theberge.cardsexerciseserver.model.Card;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.GameDeck;
import name.theberge.cardsexerciseserver.repository.GameDeckRepository;
import name.theberge.cardsexerciseserver.service.GameDeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameDeckServiceImpl implements GameDeckService {

    @Autowired
    GameDeckRepository gameDeckRepository;

    @Override
    public void addACardDeck(GameDeck gameDeck, CardDeck cardDeck) {

    }

    @Override
    public Card dealAcard(GameDeck gameDeck) {
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

    @Override
    public GameDeck getByGame(Game game) {
        return null;
    }

    @Override
    public void deleteByGame(Game game) {

    }
}
