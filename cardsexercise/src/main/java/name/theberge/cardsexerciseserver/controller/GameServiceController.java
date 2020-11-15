package name.theberge.cardsexerciseserver.controller;

import java.util.UUID;
import name.theberge.cardsexerciseserver.dto.CreateDeckResponse;
import name.theberge.cardsexerciseserver.dto.CreateGameResponse;
import name.theberge.cardsexerciseserver.dto.CreatePlayerResponse;
import name.theberge.cardsexerciseserver.dto.DealerRequest;
import name.theberge.cardsexerciseserver.dto.DeckAssignmentRequest;
import name.theberge.cardsexerciseserver.dto.GetCardsBySuitAndValueResponse;
import name.theberge.cardsexerciseserver.dto.GetCardsBySuitResponse;
import name.theberge.cardsexerciseserver.dto.GetGamePlayersResponse;
import name.theberge.cardsexerciseserver.dto.GetHandResponse;
import name.theberge.cardsexerciseserver.formatter.GamePlayersResponseFormatter;
import name.theberge.cardsexerciseserver.formatter.GetCardsBySuitAndValueFormatter;
import name.theberge.cardsexerciseserver.formatter.GetCardsBySuitFormatter;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.Player;
import name.theberge.cardsexerciseserver.service.CardDeckService;
import name.theberge.cardsexerciseserver.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameServiceController {

  GameService gameService;
  CardDeckService cardDeckService;

  @Autowired
  public GameServiceController(GameService gs, CardDeckService cds) {
    gameService = gs;
    cardDeckService = cds;
  }

  @PostMapping(value = "/v1/games")
  @ResponseStatus(HttpStatus.CREATED)
  public CreateGameResponse createGame() {
    Game createdGame = gameService.create();
    return new CreateGameResponse(createdGame);
  }

  @DeleteMapping(value = "/v1/games/{gameId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteGame(@PathVariable UUID gameId) {
    gameService.delete(gameId);
  }

  @PostMapping(value = "/v1/decks")
  @ResponseStatus(HttpStatus.CREATED)
  public CreateDeckResponse createDeck() {
    CardDeck createdDeck = cardDeckService.create();
    return new CreateDeckResponse(createdDeck);
  }

  @PostMapping(value = "/v1/games/{gameId}/deckassignments")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void addDeckToGameDeck(@PathVariable UUID gameId,
                                @RequestBody DeckAssignmentRequest deckAssignmentRequest) {
    gameService.addACardDeck(gameId, deckAssignmentRequest.getDeckId());
  }

  @PostMapping(value = "/v1/games/{gameId}/players")
  @ResponseStatus(HttpStatus.CREATED)
  public CreatePlayerResponse addPlayer(@PathVariable UUID gameId) {
    Player createdPlayer = gameService.createPlayer(gameId);
    return new CreatePlayerResponse(createdPlayer);
  }

  @DeleteMapping(value = "/v1/games/{gameId}/players/{playerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removePlayer(@PathVariable UUID gameId,
                           @PathVariable UUID playerId) {
    gameService.removePlayer(gameId, playerId);
  }

  // Shameless inspiration from
  // https://stackoverflow.com/questions/40315095/representing-moving-a-resource-in-a-restful-way
  @PostMapping(value = "/v1/games/{gameId}/dealer")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void dealCards(@PathVariable UUID gameId,
                        @RequestBody DealerRequest dealerRequest) {
    gameService.dealCards(gameId, dealerRequest.getPlayerId(), dealerRequest.getCount());
  }

  @GetMapping(value = "/v1/games/{gameId}/players/{playerId}/hand")
  public GetHandResponse getPlayerHand(@PathVariable UUID gameId,
                                       @PathVariable UUID playerId) {
    return new GetHandResponse(gameService.getCardsForPlayer(gameId, playerId));
  }

  @GetMapping(value = "/v1/games/{gameId}/players")
  public GetGamePlayersResponse getPlayers(@PathVariable UUID gameId) {
    return GamePlayersResponseFormatter.toGamePlayersResponse(gameService.getPlayers(gameId));
  }

  @GetMapping(value = "/v1/games/{gameId}/gamedeck", produces = {"application/bysuit+json"})
  public GetCardsBySuitResponse getUndealtCardsBySuit(@PathVariable UUID gameId) {
    return GetCardsBySuitFormatter
        .toGetCardsBySuitResponseFormatter(gameService.getUndealtCardCountBySuit(gameId));
  }

  @GetMapping(value = "/v1/games/{gameId}/gamedeck", produces = {
      "application/bysuitandvalue+json", MediaType.APPLICATION_JSON_VALUE})
  public GetCardsBySuitAndValueResponse getUndealtCardsBySuitAndValue(@PathVariable UUID gameId) {
    return GetCardsBySuitAndValueFormatter
        .toGetCardsBySuitAndValueResponseFormatter(
            gameService.getUndealtCardCountBySuitAndValue(gameId));

  }

  @PostMapping(value = "/v1/games/{gameId}/gamedeck/shuffleaction")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void shuffle(@PathVariable UUID gameId) {
    gameService.shuffle(gameId);
  }
}
