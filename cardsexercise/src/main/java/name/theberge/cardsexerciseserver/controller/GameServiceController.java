package name.theberge.cardsexerciseserver.controller;
import name.theberge.cardsexerciseserver.model.*;
import name.theberge.cardsexerciseserver.service.CardDeckService;
import name.theberge.cardsexerciseserver.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameServiceController {

    GameService gameService;
    CardDeckService cardDeckService;

    @Autowired
    public GameServiceController(GameService gs, CardDeckService cds) {
        gameService = gs;
        cardDeckService = cds;
    }

    @PostMapping(value = "/v1/game")
    public void createGame() {
        Game createdGame = gameService.create();
        // TODO: Format as a CreateGameResponse
    }

    @DeleteMapping(value = "/v1/game/{gameId}")
    public void deleteGame(@RequestParam String gameId) {
        //TODO; Construct a Game Object from a DeleteGameRequest
        gameService.delete(new Game());
    }

    @PostMapping(value = "/v1/deck")
    public void createDeck() {
        CardDeck createdDeck = cardDeckService.create();
        // TODO: Format as a CreateGameResponse
    }

    @PostMapping(value = "/v1/game/{gameId}/deck")
    public void addDeckToGameDeck(@RequestParam String gameId) {
        gameService.addACardDeck(new CardDeck());
        //TODO SuccessResponse
    }

    @PostMapping(value = "/v1/game/{gameId}/player")
    public void addPlayer(@RequestParam String gameId) {
        gameService.addPlayer(new Game(), new Player());
    }

    @DeleteMapping(value = "/v1/game/{gameId}/player/{playerId}")
    public void removePlayer(@RequestParam String gameId,
                             @RequestParam String playerId) {
        gameService.removePlayer(new Game(), new Player());
    }

    @PostMapping(value = "/v1/game/{gameId}/dealACard")
    public void dealACard(@RequestParam String gameId) {
        //TODO: Get the player from the body
        gameService.dealAcard(new Game(), new Player());
    }

    @GetMapping(value = "/v1/game/{gameId}/player/{playerId}/cards")
    public void getPlayerCards(@RequestParam String gameId,
                               @RequestParam String playerId) {
        gameService.getCardsForPlayer(new Game(), new Player());
    }

    @GetMapping(value = "/v1/game/{gameId}/player")
    public void getPlayers(@RequestParam String gameId) {
        gameService.getPlayers(new Game());
    }

    @GetMapping(value = "/v1/game/{gameId}/cards")
    //TODO: Think of an accept header for undealt count or aggregate by
    public void getUndealtCards(@RequestParam String gameId) {
        gameService.getUndealtCardCount(new Game());
    }

    @PostMapping(value = "/v1/game/{gameId}/shuffle")
    public void shuffle(@RequestParam String gameId) {
        gameService.shuffle(new Game());
    }
}
