package name.theberge.cardsexerciseserver.unit.service.implementation;

import name.theberge.cardsexerciseserver.model.Card;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.GameDeck;
import name.theberge.cardsexerciseserver.unit.repository.GameDeckRepository;
import name.theberge.cardsexerciseserver.unit.service.GameDeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    public GameDeck getByGameId(UUID gameId) {
        return null;
    }

    @Override
    public void deleteByGameId(UUID gameId) {

    }
}
