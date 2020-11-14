package name.theberge.cardsexerciseserver.service.implementation;

import name.theberge.cardsexerciseserver.exception.GameServiceException;
import name.theberge.cardsexerciseserver.exception.PlayerNotFoundException;
import name.theberge.cardsexerciseserver.model.*;
import name.theberge.cardsexerciseserver.repository.GameRepository;
import name.theberge.cardsexerciseserver.service.CardDeckService;
import name.theberge.cardsexerciseserver.service.GameDeckService;
import name.theberge.cardsexerciseserver.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

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
        return game;
    }

    @Override
    public void delete(Game game) {
        gameDeckService.deleteByGame(game);
        gameRepository.delete(game);
    }

    @Override
    public void addPlayer(Game game, Player player) {
        game.addPlayer(player);
    }

    @Override
    public void removePlayer(Game game, Player player) {
        game.removePlayer(player);
    }

    @Override
    public Collection<Card> getCardsForPlayer(Game game, Player player) throws PlayerNotFoundException {
        Game gameFromRepo = gameRepository.get(game);
        return getGamePlayer(gameFromRepo, player).getCards();
    }

    @Override
    public Collection<Player> getPlayers(Game game) {
        Game gameFromRepo = gameRepository.get(game);
        return gameFromRepo.getPlayers();
    }

    @Override
    public void addACardDeck(Game game, CardDeck cardDeck) {
        //TODO: Check if it exists
        GameDeck gameDeckFromRepo = gameDeckService.getByGame(game);
        gameDeckService.addACardDeck(gameDeckFromRepo, cardDeck);
        cardDeckService.delete(cardDeck);
    }

    @Override
    public Card dealACard(Game game, Player player) {
        Game gameFromRepo = gameRepository.get(game);
        GameDeck gameDeckFromRepo = gameDeckService.getByGame(game);
        Player playerFromGame = getGamePlayer(gameFromRepo, player);
        Card dealtCard = gameDeckService.dealAcard(gameDeckFromRepo);
        playerFromGame.receiveACard(dealtCard);
        gameRepository.update(gameFromRepo);
        return dealtCard;
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

    private Player getGamePlayer(Game game, Player player) {
        return game.getPlayers().stream()
                .filter(p -> p.getId().equals(player.getId()))
                .findFirst()
                .orElseThrow(PlayerNotFoundException::new);
    }
}
