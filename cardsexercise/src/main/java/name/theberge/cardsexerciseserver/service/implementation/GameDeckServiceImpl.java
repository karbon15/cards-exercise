package name.theberge.cardsexerciseserver.service.implementation;

import name.theberge.cardsexerciseserver.exception.InvalidDealCountException;
import name.theberge.cardsexerciseserver.model.*;
import name.theberge.cardsexerciseserver.repository.GameDeckRepository;
import name.theberge.cardsexerciseserver.service.GameDeckService;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

        Collection<Card> cards = gameDeck.dealCards(howMany);
        gameDeckRepository.update(gameDeck);

        return cards;
    }

    // Didnt have it on top of my head, how to get it as Integer, rather than the long of Collectors.counting()
    // Thanks to https://stackoverflow.com/posts/50803660/revisions
    @Override
    public Map<CardSuite, Integer> getUndealtCardCountBySuit(UUID gameId) {
        // Depending on the database technology, eg SQL, aggregations at that level would be way more performant
        // than fetching the whole dataset
        return getByGameId(gameId).getUndealtCards().stream()
                .collect(Collectors.groupingBy(
                        Card::getSuite,
                        Collectors.reducing(0, e -> 1, Integer::sum)));
    }

    @Override
    public Map<Pair<CardSuite, CardFaceValue>, Integer> getUndealtCardCountBySuitAndValue(UUID gameId) {
        // Depending on the database technology, eg SQL, aggregations at that level would be way more performant
        // than fetching the whole dataset
        return getByGameId(gameId).getUndealtCards().stream()
                .collect(Collectors.groupingBy(
                        card -> Pair.with(card.getSuite(), card.getFaceValue()),
                        Collectors.reducing(0, e -> 1, Integer::sum)));
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
