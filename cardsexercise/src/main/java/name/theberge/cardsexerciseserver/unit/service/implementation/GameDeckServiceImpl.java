package name.theberge.cardsexerciseserver.unit.service.implementation;

import name.theberge.cardsexerciseserver.exception.InvalidDealCountException;
import name.theberge.cardsexerciseserver.model.Card;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.model.GameDeck;
import name.theberge.cardsexerciseserver.unit.repository.GameDeckRepository;
import name.theberge.cardsexerciseserver.unit.service.GameDeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        //Avoid surprises.  It is a reasonable expectation that a deck is in random order
        cardDeck.shuffle();
        gameDeck.addADeck(cardDeck);
        gameDeckRepository.update(gameDeck);
    }

    @Override
    public Collection<Card> dealCards(UUID gameId, int howMany) {
        if (howMany < 1) {
            throw new InvalidDealCountException("Cards to deal must be >= 1");
        }

        GameDeck gameDeck = getByGameId(gameId);

        if (howMany > gameDeck.getUndealtCards().size()) {
            throw new InvalidDealCountException("Cards to deal must be < than the amount of undealt cards");
        }

        List listOfCards = ((LinkedList<Card>)gameDeck.getUndealtCards()).subList(0, howMany);
        LinkedList<Card> toReturn = new LinkedList<>(listOfCards);

        listOfCards.clear();
        gameDeckRepository.update(gameDeck);

        return toReturn;

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
