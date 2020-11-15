package name.theberge.cardsexerciseserver.controller;
import name.theberge.cardsexerciseserver.dto.*;
import name.theberge.cardsexerciseserver.formatter.GamePlayersResponseFormatter;
import name.theberge.cardsexerciseserver.model.*;
import name.theberge.cardsexerciseserver.service.CardDeckService;
import name.theberge.cardsexerciseserver.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public void deleteGame(@PathVariable UUID gameId) {
        gameService.delete(gameId);;
    }

    @PostMapping(value = "/v1/decks")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateDeckResponse createDeck() {
        CardDeck createdDeck = cardDeckService.create();
        return new CreateDeckResponse(createdDeck);
    }

    @PostMapping(value = "/v1/games/{gameId}/deckassignments")
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
    public void removePlayer(@PathVariable UUID gameId,
                             @PathVariable UUID playerId) {
        gameService.removePlayer(gameId, playerId);
    }

    // Shameless inspiration from
    // https://stackoverflow.com/questions/40315095/representing-moving-a-resource-in-a-restful-way
    @PostMapping(value = "/v1/games/{gameId}/dealer")
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

    @GetMapping(value = "/v1/games/{gameId}/cards")
    //TODO: Think of an accept header for undealt count or aggregate by
    public void getUndealtCards(@RequestParam String gameId) {
        gameService.getUndealtCardCount(new Game());
    }

    @PostMapping(value = "/v1/games/{gameId}/shuffle")
    public void shuffle(@RequestParam String gameId) {
        gameService.shuffle(new Game());
    }
}
