package name.theberge.cardsexerciseserver.unit.repository;

import name.theberge.cardsexerciseserver.exception.DeckAlreadyExistsException;
import name.theberge.cardsexerciseserver.exception.DeckNotFoundException;
import name.theberge.cardsexerciseserver.exception.GameAlreadyExistsException;
import name.theberge.cardsexerciseserver.exception.GameNotFoundException;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class CardDeckRepositoryImplTests {

    @Autowired
    CardDeckRepository cardDeckRepository;

    @Test
    @DisplayName("Creating a card deck should allow reading back that same deck")
    void creatingADeck() {
        CardDeck deck = new CardDeck();

        cardDeckRepository.create(deck);
        CardDeck readDeck = cardDeckRepository.getById(deck.getId());

        Assertions.assertEquals(deck.getId(), readDeck.getId());
    }

    @Test
    @DisplayName("Creating a deck that already exists")
    void creatingADeckAlreadyExisting() {
        CardDeck deck = new CardDeck();

        cardDeckRepository.create(deck);
        Assertions.assertThrows(DeckAlreadyExistsException.class, () -> cardDeckRepository.create(deck));
    }

    @Test
    @DisplayName("An updated deck should have the updated properties when read back")
    void updatingADeck() {
        CardDeck deck = new CardDeck();

        cardDeckRepository.create(deck);

        //Simulate that we persisted in aa database by cloning
        CardDeck updatedDeck = new CardDeck(deck);
        updatedDeck.setIsUsed(true);
        cardDeckRepository.update(updatedDeck);
        CardDeck readDeck = cardDeckRepository.getById(deck.getId());
        Assertions.assertEquals(updatedDeck.getIsUsed(), readDeck.getIsUsed());
    }

    @Test
    @DisplayName("Updating a deck that doesnt exist should throw an exception")
    void updatingAGameNotFound() {
        CardDeck deck = new CardDeck();
        Assertions.assertThrows(DeckNotFoundException.class, () -> cardDeckRepository.update(deck));
    }

    @Test
    @DisplayName("Getting a game that doesnt exist")
    void gettingAGameNotFound() {
        Assertions.assertThrows(DeckNotFoundException.class, () -> cardDeckRepository.getById(UUID.randomUUID()));
    }
}
