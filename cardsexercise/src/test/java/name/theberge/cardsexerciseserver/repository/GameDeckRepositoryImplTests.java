package name.theberge.cardsexerciseserver.repository;

import name.theberge.cardsexerciseserver.exception.DeckAlreadyExistsException;
import name.theberge.cardsexerciseserver.exception.DeckNotFoundException;
import name.theberge.cardsexerciseserver.model.Card;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.model.GameDeck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class GameDeckRepositoryImplTests {

  @Autowired
  GameDeckRepository gameDeckRepository;

  @Test
  @DisplayName("Creating a card deck should allow reading back that same deck")
  void creatingADeck() {
    GameDeck deck = new GameDeck();
    deck.setGameId(UUID.randomUUID());

    gameDeckRepository.create(deck);
    CardDeck readDeck = gameDeckRepository.getByGame(deck.getGameId());

    Assertions.assertEquals(deck.getId(), readDeck.getId());
  }

  @Test
  @DisplayName("Creating a deck that already exists")
  void creatingADeckAlreadyExisting() {
    GameDeck deck = new GameDeck();
    deck.setGameId(UUID.randomUUID());

    gameDeckRepository.create(deck);
    Assertions
        .assertThrows(DeckAlreadyExistsException.class, () -> gameDeckRepository.create(deck));
  }

  @Test
  @DisplayName("An updated deck should have the updated properties when read back")
  void updatingADeck() {
    GameDeck deck = new GameDeck();
    deck.setGameId(UUID.randomUUID());

    gameDeckRepository.create(deck);

    //Simulate that we persisted in aa database by cloning
    GameDeck updatedDeck = new GameDeck(deck);

    CardDeck aCardDeck = new CardDeck();
    updatedDeck.addADeck(aCardDeck);
    gameDeckRepository.update(updatedDeck);
    CardDeck readDeck = gameDeckRepository.getByGame(deck.getGameId());
    Assertions.assertEquals(52, updatedDeck.getUndealtCards().size());
  }

  @Test
  @DisplayName("Updating a deck that doesnt exist should throw an exception")
  void updatingADeckNotFound() {
    GameDeck deck = new GameDeck();
    deck.setGameId(UUID.randomUUID());
    Assertions.assertThrows(DeckNotFoundException.class, () -> gameDeckRepository.update(deck));
  }

  @Test
  @DisplayName("Getting a card deck that doesnt exist")
  void gettingADeckNotFound() {
    Assertions.assertThrows(DeckNotFoundException.class,
        () -> gameDeckRepository.getByGame(UUID.randomUUID()));
  }

  @Test
  @DisplayName("An deleted deck should not be reachable")
  void deletingADeck() {
    GameDeck deck = new GameDeck();
    deck.setGameId(UUID.randomUUID());

    gameDeckRepository.create(deck);
    gameDeckRepository.delete(deck.getGameId());
    Assertions.assertThrows(DeckNotFoundException.class,
        () -> gameDeckRepository.getByGame(deck.getGameId()));
  }
}
