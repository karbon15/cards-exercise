package name.theberge.cardsexerciseserver.unit.service;

import name.theberge.cardsexerciseserver.exception.InvalidDealCountException;
import name.theberge.cardsexerciseserver.model.Card;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.GameDeck;
import name.theberge.cardsexerciseserver.unit.repository.CardDeckRepository;
import name.theberge.cardsexerciseserver.unit.repository.GameDeckRepository;
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

}
