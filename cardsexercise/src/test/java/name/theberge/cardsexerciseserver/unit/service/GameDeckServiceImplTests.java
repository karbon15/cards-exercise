package name.theberge.cardsexerciseserver.unit.service;

import name.theberge.cardsexerciseserver.model.CardDeck;
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
}
