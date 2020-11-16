package name.theberge.cardsexerciseserver.service.implementation;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import name.theberge.cardsexerciseserver.exception.DeckAlreadyUsedException;
import name.theberge.cardsexerciseserver.exception.PlayerNotFoundException;
import name.theberge.cardsexerciseserver.model.Card;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.model.CardFaceValue;
import name.theberge.cardsexerciseserver.model.CardSuit;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.GameDeck;
import name.theberge.cardsexerciseserver.model.Player;
import name.theberge.cardsexerciseserver.repository.GameRepository;
import name.theberge.cardsexerciseserver.service.CardDeckService;
import name.theberge.cardsexerciseserver.service.GameDeckService;
import name.theberge.cardsexerciseserver.service.GameService;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public Collection<Card> getCardsForPlayer(UUID gameId, UUID playerId) {
    Game gameFromRepo = gameRepository.get(gameId);
    Player playerFromRepo = getGamePlayer(gameFromRepo, playerId);
    return playerFromRepo.getCards();
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
  public Collection<Player> getPlayers(UUID gameId) {
    Game gameFromRepo = gameRepository.get(gameId);
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
  public Map<CardSuit, Integer> getUndealtCardCountBySuit(UUID gameId) {
    return gameDeckService.getUndealtCardCountBySuit(gameId);

  }

  @Override
  public Map<Pair<CardSuit, CardFaceValue>, Integer> getUndealtCardCountBySuitAndValue(
      UUID gameId) {
    return gameDeckService.getUndealtCardCountBySuitAndValue(gameId);
  }

  @Override
  public void shuffle(UUID gameId) {
    gameDeckService.shuffle(gameId);
  }

  private Player getGamePlayer(Game game, UUID playerId) {
    return game.getPlayers().stream()
        .filter(p -> p.getId().equals(playerId))
        .findFirst()
        .orElseThrow(PlayerNotFoundException::new);
  }
}
