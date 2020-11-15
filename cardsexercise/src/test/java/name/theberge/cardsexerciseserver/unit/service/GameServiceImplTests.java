package name.theberge.cardsexerciseserver.unit.service;

import name.theberge.cardsexerciseserver.exception.DeckAlreadyUsedException;
import name.theberge.cardsexerciseserver.model.*;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@SpringBootTest
public class GameServiceImplTests {
    @MockBean
    GameRepository gameRepository;

    @MockBean
    GameDeckService gameDeckService;

    @MockBean
    CardDeckService cardDeckService;

    @Autowired
    GameService gameService;
;

    @Test
    @DisplayName("Creating a game should generate an id and call the repository")
    void creatingAGame() {
        Mockito.when(gameRepository.create(Mockito.any(Game.class)))
            .then(AdditionalAnswers.returnsFirstArg());

        Mockito.when(gameDeckService.create(Mockito.any(UUID.class)))
                .thenReturn(new GameDeck());

        Game createdGame = gameService.create();

        Mockito.verify(gameRepository).create(ArgumentMatchers.argThat(game -> game.getId() != null));
        Mockito.verify(gameDeckService).create(ArgumentMatchers.argThat(id -> id.equals(createdGame.getId())));
    }

    @Test
    @DisplayName("Deleting a game should delete the game and cascade to the game deck service")
    void deletingAGame() {
        Game theGame = new Game();

        gameService.delete(theGame.getId());

        Mockito.verify(gameRepository).delete(ArgumentMatchers.argThat(id -> id.equals(theGame.getId())));
        Mockito.verify(gameDeckService).deleteByGameId(ArgumentMatchers.argThat(id -> id.equals(theGame.getId())));
    }

    @Test
    @DisplayName("Adding a unused CardDeck to the Game Deck")
    void addingADeckToTheGameDeck() {
        UUID cardDeckId = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();

        GameDeck gameDeck = new GameDeck();
        CardDeck cardDeck = new CardDeck();

        Mockito.when(gameDeckService.getByGameId(Mockito.eq(gameId)))
                .thenReturn(gameDeck);
        Mockito.when(cardDeckService.getById(Mockito.eq(cardDeckId)))
                .thenReturn(cardDeck);
        Mockito.when(cardDeckService.update(Mockito.eq(cardDeck)))
                .thenReturn(gameDeck);

        gameService.addACardDeck(gameId, cardDeckId);

        Mockito.verify(gameDeckService).getByGameId(ArgumentMatchers.argThat(id -> id.equals(gameId)));
        Mockito.verify(gameDeckService).addACardDeck(
                ArgumentMatchers.argThat(gd -> gd.equals(gameDeck)),
                ArgumentMatchers.argThat(cd -> cd.equals(cardDeck)));
        Mockito.verify(cardDeckService).getById(ArgumentMatchers.argThat(id -> id.equals(cardDeckId)));
        Mockito.verify(cardDeckService).update(ArgumentMatchers.argThat(cd -> cd.getIsUsed()));
    }

    @Test
    @DisplayName("Adding a used CardDeck to the Game Deck should throw")
    void addingAUsedDeckToTheGameDeck() {
        UUID cardDeckId = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();

        GameDeck gameDeck = new GameDeck();
        CardDeck cardDeck = new CardDeck();
        cardDeck.setIsUsed(true);

        Mockito.when(gameDeckService.getByGameId(Mockito.eq(gameId)))
                .thenReturn(gameDeck);
        Mockito.when(cardDeckService.getById(Mockito.eq(cardDeckId)))
                .thenReturn(cardDeck);
        Mockito.when(cardDeckService.update(Mockito.eq(cardDeck)))
                .thenReturn(gameDeck);

        Assertions.assertThrows(DeckAlreadyUsedException.class, () -> gameService.addACardDeck(gameId, cardDeckId));
    }

    @Test
    @DisplayName("Adding a player to a game")
    void addingAPlayerToTheGame() {
        UUID gameId = UUID.randomUUID();

        Game game = new Game();

        Mockito.when(gameRepository.get(Mockito.eq(gameId)))
                .thenReturn(game);

        Mockito.when(gameRepository.update(Mockito.eq(game)))
                .thenReturn(game);

        gameService.createPlayer(gameId);

        Mockito.verify(gameRepository).get(ArgumentMatchers.argThat(id -> id.equals(gameId)));
        Mockito.verify(gameRepository).update(ArgumentMatchers.argThat(g -> g.equals(game)));

        Assertions.assertEquals(1, game.getPlayers().size());
    }

    @Test
    @DisplayName("Removing players from the game")
    void removingAPlayerFromTheGame() {
        UUID gameId = UUID.randomUUID();

        Game game = new Game();
        Player player = new Player();
        game.addPlayer(player);

        Mockito.when(gameRepository.get(Mockito.eq(gameId)))
                .thenReturn(game);

        Mockito.when(gameRepository.update(Mockito.eq(game)))
                .thenReturn(game);

        gameService.removePlayer(gameId, player.getId());

        Mockito.verify(gameRepository).get(ArgumentMatchers.argThat(id -> id.equals(gameId)));
        Mockito.verify(gameRepository).update(ArgumentMatchers.argThat(g -> g.equals(game)));

        Assertions.assertEquals(0, game.getPlayers().size());
    }

    @Test
    @DisplayName("Dealing cards")
    void dealingCards() {
        int howMany = 5;

        UUID gameId = UUID.randomUUID();

        Game game = new Game();
        Player player = new Player();
        game.addPlayer(player);

        CardDeck cardDeck = new CardDeck();
        GameDeck gameDeck = new GameDeck();
        gameDeck.addADeck(cardDeck);

        Collection<Card> dealtCards = new ArrayList(gameDeck.getUndealtCards()).subList(0, howMany);

        Mockito.when(gameRepository.get(Mockito.eq(gameId)))
                .thenReturn(game);

        Mockito.when(gameDeckService.dealCards(Mockito.eq(gameId), Mockito.eq(howMany)))
                .thenReturn(dealtCards);

        Mockito.when(gameRepository.update(Mockito.eq(game)))
                .thenReturn(game);

        gameService.dealCards(gameId, player.getId(), howMany);

        Mockito.verify(gameRepository).get(ArgumentMatchers.argThat(id -> id.equals(gameId)));
        Mockito.verify(gameDeckService).dealCards(Mockito.eq(gameId), Mockito.eq(howMany));
        Mockito.verify(gameRepository).update(ArgumentMatchers.argThat(g -> g.equals(game)));

        Assertions.assertEquals(dealtCards, player.getCards());


    }
}
