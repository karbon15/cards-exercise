package name.theberge.cardsexerciseserver.unit.service.implementation;

import name.theberge.cardsexerciseserver.exception.DeckAlreadyUsedException;
import name.theberge.cardsexerciseserver.exception.PlayerNotFoundException;
import name.theberge.cardsexerciseserver.model.*;
import name.theberge.cardsexerciseserver.unit.repository.GameRepository;
import name.theberge.cardsexerciseserver.unit.service.CardDeckService;
import name.theberge.cardsexerciseserver.unit.service.GameDeckService;
import name.theberge.cardsexerciseserver.unit.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDeckService gameDeckService;
    @Autowired
    private CardDeckService cardDeckService;
    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game create() {
        Game game = new Game();
        gameRepository.create(game);
        gameDeckService.create(game.getId());
        return game;
    }

    @Override
    public void delete(UUID gameId) {
        gameDeckService.deleteByGameId(gameId);
        gameRepository.delete(gameId);
    }

    @Override
    public Player createPlayer(UUID gameId) {
        Game gameFromRepo = gameRepository.get(gameId);
        Player createdPlayer = new Player();
        gameFromRepo.addPlayer(createdPlayer);
        gameRepository.update(gameFromRepo);
        return createdPlayer;
    }

    @Override
    public void removePlayer(UUID gameId, UUID playerId) {
        Game gameFromRepo = gameRepository.get(gameId);
        // No exception here if player isn't part of the game as DELETE is considered idempotent in REST
        gameFromRepo.removePlayer(playerId);
        gameRepository.update(gameFromRepo);
    }

    @Override
    public Collection<Card> getCardsForPlayer(Game game, Player player) throws PlayerNotFoundException {
        Game gameFromRepo = gameRepository.get(game.getId());
        //return getGamePlayer(gameFromRepo, player).getCards();
        return null;
    }

    @Override
    public Collection<Card> dealCards(UUID gameId, UUID playerId, int howMany) {
        Game gameFromRepo = gameRepository.get(gameId);
        Player playerFromGame = getGamePlayer(gameFromRepo, playerId);
        Collection<Card> dealtCards = gameDeckService.dealCards(gameId, howMany);
        playerFromGame.receiveCards(dealtCards);
        gameRepository.update(gameFromRepo);
        return dealtCards;
    }

    @Override
    public Collection<Player> getPlayers(Game game) {
        Game gameFromRepo = gameRepository.get(game.getId());
        return gameFromRepo.getPlayers();
    }

    @Override
    public void addACardDeck(UUID gameId, UUID deckId) {
        GameDeck gameDeckFromRepo = gameDeckService.getByGameId(gameId);
        CardDeck cardDeckFromRepo = cardDeckService.getById(deckId);
        if (cardDeckFromRepo.getIsUsed()) {
            throw new DeckAlreadyUsedException();
        }
        gameDeckService.addACardDeck(gameDeckFromRepo, cardDeckFromRepo);
        cardDeckFromRepo.setIsUsed(true);
        cardDeckService.update(cardDeckFromRepo);
    }


    @Override
    public int getUndealtCardCount(Game game) {
        return gameDeckService.getUndealtCardCount();

    }

    @Override
    public int getUndealtCardCountBySuitAndValue(Game game) {
        return 0;
    }

    @Override
    public void shuffle(Game game) {
        gameDeckService.shuffle();
    }

    private Player getGamePlayer(Game game, UUID playerId) {
        return game.getPlayers().stream()
                .filter(p -> p.getId().equals(playerId))
                .findFirst()
                .orElseThrow(PlayerNotFoundException::new);
    }
}
