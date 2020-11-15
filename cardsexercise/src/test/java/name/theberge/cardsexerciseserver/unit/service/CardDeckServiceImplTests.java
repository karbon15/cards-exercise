package name.theberge.cardsexerciseserver.unit.service;

import name.theberge.cardsexerciseserver.model.Card;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.unit.repository.CardDeckRepository;
import name.theberge.cardsexerciseserver.unit.repository.GameRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

@SpringBootTest
public class CardDeckServiceImplTests {
    @MockBean
    CardDeckRepository deckRepository;

    @Autowired
    CardDeckService deckService;
;

    @Test
    @DisplayName("Creating a deck should generate an id and call the repository")
    void creatingADeck() {
        Mockito.when(deckRepository.create(Mockito.any(CardDeck.class)))
            .then(AdditionalAnswers.returnsFirstArg());

        deckService.create();

        Mockito.verify(deckRepository).create(ArgumentMatchers.argThat(deck -> deck.getId() != null));
    }

    @Test
    @DisplayName("Updating a deck should call the repository")
    void updatingADeck() {
        CardDeck deck = new CardDeck();

        deckService.update(deck);

        Mockito.verify(deckRepository).update(ArgumentMatchers.argThat(deckArg -> deckArg.equals(deck)));
    }

    @Test
    @DisplayName("Getting a deck should call the repository")
    void gettingADeck() {
        UUID someId = UUID.randomUUID();
        CardDeck someCardDeck = new CardDeck();

        Mockito.when(deckRepository.getById(Mockito.eq(someId)))
                .thenReturn(someCardDeck);

        CardDeck readCardDeck = deckService.getById(someId);
        Assertions.assertEquals(someCardDeck, readCardDeck);

        Mockito.verify(deckRepository).getById(ArgumentMatchers.argThat(id -> id.equals(someId)));
    }
}
