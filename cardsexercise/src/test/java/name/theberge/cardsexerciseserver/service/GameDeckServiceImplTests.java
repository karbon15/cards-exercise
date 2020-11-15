package name.theberge.cardsexerciseserver.service;

import name.theberge.cardsexerciseserver.exception.InvalidDealCountException;
import name.theberge.cardsexerciseserver.model.*;
import name.theberge.cardsexerciseserver.repository.GameDeckRepository;
import org.javatuples.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
public class GameDeckServiceImplTests {
    @MockBean
    GameDeckRepository deckRepository;

    @Autowired
    GameDeckService deckService;
;

    @Test
    @DisplayName("Creating a deck should generate an id and call the repository")
    void creatingADeck() {
        UUID gameId = UUID.randomUUID();
        Mockito.when(deckRepository.create(Mockito.any(GameDeck.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        deckService.create(gameId);

        Mockito.verify(deckRepository).create(ArgumentMatchers.argThat(deck -> deck.getId() != null));
    }

    @Test
    @DisplayName("Deleting a deck should call the repository")
    void deletingADeck() {
        UUID gameId = UUID.randomUUID();
        deckService.deleteByGameId(gameId);

        Mockito.verify(deckRepository).delete(Mockito.eq(gameId));
    }


    @Test
    @DisplayName("Dealing cards from a deck")
    void dealingCards() {
        int howMany = 12;
        UUID gameId = UUID.randomUUID();

        GameDeck gameDeck = new GameDeck();
        CardDeck cardDeck = new CardDeck();

        gameDeck.addADeck(cardDeck);

        Mockito.when(deckRepository.getByGame(Mockito.eq(gameId)))
                .thenReturn(gameDeck);

        Mockito.when(deckRepository.update(Mockito.eq(gameDeck)))
                .thenReturn(gameDeck);

        Collection<Card> cardsDealt = deckService.dealCards(gameId, howMany);

        Mockito.verify(deckRepository).getByGame(Mockito.eq(gameId));
        Mockito.verify(deckRepository).update(Mockito.eq(gameDeck));

        Assertions.assertEquals(howMany, cardsDealt.size());
        Assertions.assertEquals(52 - howMany, gameDeck.getUndealtCards().size());
    }

    @Test
    @DisplayName("Dealing zero cards should throw")
    void dealingZeroCards() {
        int howMany = 0;
        UUID gameId = UUID.randomUUID();

        Assertions.assertThrows(InvalidDealCountException.class, () -> deckService.dealCards(gameId, howMany));
    }

    @Test
    @DisplayName("Dealing negative cards should throw")
    void dealingNegativeCards() {
        int howMany = -5;
        UUID gameId = UUID.randomUUID();

        Assertions.assertThrows(InvalidDealCountException.class, () -> deckService.dealCards(gameId, howMany));
    }

    @Test
    @DisplayName("Dealing more cards than the deck has")
    void dealingTooManyCardsCards() {
        int howMany = 54;
        UUID gameId = UUID.randomUUID();

        GameDeck gameDeck = new GameDeck();
        CardDeck cardDeck = new CardDeck();

        gameDeck.addADeck(cardDeck);

        Mockito.when(deckRepository.getByGame(Mockito.eq(gameId)))
                .thenReturn(gameDeck);

        Assertions.assertThrows(InvalidDealCountException.class, () -> deckService.dealCards(gameId, howMany));

        Mockito.verify(deckRepository).getByGame(Mockito.eq(gameId));
    }

    @Test
    @DisplayName("Getting undealt cards by suit")
    public void gettingUndealtCardsBySuit() {
        UUID gameId = UUID.randomUUID();

        GameDeck gameDeck = new GameDeck();
        CardDeck cardDeck1 = new CardDeck();
        CardDeck cardDeck2 = new CardDeck();

        gameDeck.addADeck(cardDeck1);
        gameDeck.addADeck(cardDeck2);

        Mockito.when(deckRepository.getByGame(Mockito.eq(gameId)))
                .thenReturn(gameDeck);

        Map<CardSuit, Integer> counts = deckService.getUndealtCardCountBySuit(gameId);

        Mockito.verify(deckRepository).getByGame(Mockito.eq(gameId));

        for (CardSuit c: CardSuit.values()) {
            Assertions.assertEquals(13 * 2, counts.get(c));
        }
    }

    @Test
    @DisplayName("Getting undealt cards by suit and value")
    public void gettingUndealtCardsBySuitAndValue() {
        UUID gameId = UUID.randomUUID();

        GameDeck gameDeck = new GameDeck();
        CardDeck cardDeck1 = new CardDeck();
        CardDeck cardDeck2 = new CardDeck();

        gameDeck.addADeck(cardDeck1);
        gameDeck.addADeck(cardDeck2);

        Mockito.when(deckRepository.getByGame(Mockito.eq(gameId)))
                .thenReturn(gameDeck);

        Map<Pair<CardSuit, CardFaceValue>, Integer> counts = deckService.getUndealtCardCountBySuitAndValue(gameId);

        Mockito.verify(deckRepository).getByGame(Mockito.eq(gameId));

        Assertions.assertEquals(52, counts.size());

        for (Pair<CardSuit, CardFaceValue> k: counts.keySet()) {
            Assertions.assertEquals(2, counts.get(k));
        }
    }
}
