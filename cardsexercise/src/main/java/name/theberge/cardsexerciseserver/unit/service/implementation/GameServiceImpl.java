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
    public void addPlayer(Game game, Player player) {
        game.addPlayer(player);
    }

    @Override
    public void removePlayer(Game game, Player player) {
        game.removePlayer(player);
    }

    @Override
    public Collection<Card> getCardsForPlayer(Game game, Player player) throws PlayerNotFoundException {
        Game gameFromRepo = gameRepository.get(game.getId());
        return getGamePlayer(gameFromRepo, player).getCards();
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
    public Card dealCards(Game game, Player player, int howMany) {
        Game gameFromRepo = gameRepository.get(game.getId());
        GameDeck gameDeckFromRepo = gameDeckService.getByGameId(game.getId());
        Player playerFromGame = getGamePlayer(gameFromRepo, player);
        Card dealtCard = gameDeckService.dealCards(gameDeckFromRepo, howMany);
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
