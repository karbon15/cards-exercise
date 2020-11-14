package name.theberge.cardsexerciseserver.unit.service;

import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.unit.repository.CardDeckRepository;
import name.theberge.cardsexerciseserver.unit.repository.GameRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
    @DisplayName("Deleting a deck should delete the game and cascade to the game deck service")
    void updatingADeck() {
        CardDeck deck = new CardDeck();

        deckService.update(deck);

        Mockito.verify(deckRepository).update(ArgumentMatchers.argThat(deckArg -> deckArg.equals(deck)));
    }
}
