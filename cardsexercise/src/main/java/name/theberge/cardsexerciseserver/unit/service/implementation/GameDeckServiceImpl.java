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
    public GameDeck create(UUID gameId) {
        GameDeck gameDeck = new GameDeck();
        gameDeck.setGameId(gameId);
        return gameDeckRepository.create(gameDeck);
    }

    @Override
    public void addACardDeck(GameDeck gameDeck, CardDeck cardDeck) {
        cardDeck.shuffle();
        gameDeck.addADeck(cardDeck);
    }

    @Override
    public Card dealCards(GameDeck gameDeck, int howMany) {
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
        return gameDeckRepository.getByGame(gameId);
    }

    @Override
    public void deleteByGameId(UUID gameId) {

    }
}
