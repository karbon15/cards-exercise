package name.theberge.cardsexerciseserver.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import name.theberge.cardsexerciseserver.dto.*;

import name.theberge.cardsexerciseserver.model.CardSuite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GameServiceControllerTests {

    private final String APPLICATION_JSON = "application/json";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldBeAbleToCreateAGame() throws Exception {
        CreateGameResponse response = createAGame();
        Assertions.assertNotNull(response.getId());
    }

    @Test
    public void shouldBeAbleToDeleteAGame() throws Exception {
        CreateGameResponse createResponse = createAGame();

        ResultActions ra =  mockMvc.perform(delete("/v1/games/" + createResponse.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldBeAbleToCreateADeck() throws Exception {
        CreateDeckResponse response = createADeck();
        Assertions.assertNotNull(response.getId());
    }

    @Test
    public void shouldBeAbleToAddADeckToGameDeck() throws Exception {
        CreateDeckResponse createdDeck = createADeck();
        CreateGameResponse createdGame = createAGame();

        assignADeck(createdGame.getId(), createdDeck.getId());

        //TODO: Observe the effects on the endpoints that provide info about the game deck
    }

    @Test
    public void shouldBeAbleToAddAPlayer() throws Exception {
        CreateGameResponse createdGame = createAGame();
        createAPlayer(createdGame);
        //TODO: Observe the effects on the future read endpoints
    }

    @Test
    public void shouldBeAbleToRemoveAPlayer() throws Exception {
        CreateGameResponse createdGame = createAGame();
        CreatePlayerResponse createdPlayer = createAPlayer(createdGame);

        ResultActions ra =  mockMvc.perform(
                delete("/v1/games/" + createdGame.getId() + "/players/" + createdPlayer.getId())
        ).andExpect(status().isOk());
        //TODO: Observe the effects on the future read endpoints
    }

    @Test
    public void shouldBeAbleToDealCards() throws Exception {
        CreateGameResponse createdGame = createAGame();
        CreatePlayerResponse createdPlayer = createAPlayer(createdGame);
        CreateDeckResponse createdDeck = createADeck();
        assignADeck(createdGame.getId(), createdDeck.getId());

        dealCards(createdGame.getId(), createdPlayer.getId(), 5);
    }

    @Test
    public void shouldBeAbleToRetrieveAPlayersHand() throws Exception {
        CreateGameResponse createdGame = createAGame();
        CreatePlayerResponse createdPlayer = createAPlayer(createdGame);
        CreateDeckResponse createdDeck = createADeck();
        assignADeck(createdGame.getId(), createdDeck.getId());
        dealCards(createdGame.getId(), createdPlayer.getId(), 5);

        GetHandResponse handResponse = getHand(createdGame.getId(), createdPlayer.getId());

        Assertions.assertEquals(5, handResponse.getCards().size());
    }

    @Test
    public void shouldBeAbleToRetrieveAGamesPlayers() throws Exception {
        CreateGameResponse createdGame = createAGame();
        CreatePlayerResponse createdPlayer = createAPlayer(createdGame);
        CreatePlayerResponse createdPlayer2 = createAPlayer(createdGame);
        CreateDeckResponse createdDeck = createADeck();
        assignADeck(createdGame.getId(), createdDeck.getId());
        dealCards(createdGame.getId(), createdPlayer.getId(), 5);
        dealCards(createdGame.getId(), createdPlayer2.getId(), 5);

        GetGamePlayersResponse gamePlayersResponse = getGamePlayers(createdGame.getId());

        Assertions.assertEquals(2, gamePlayersResponse.getPlayers().size());
        Assertions.assertEquals(
                Set.of(createdPlayer.getId(), createdPlayer2.getId()),
                gamePlayersResponse.getPlayers().stream()
                        .map(p -> p.getId())
                        .collect(Collectors.toSet()));
    }

    @Test
    public void shouldBeAbleToGetCardCountsBySuit() throws Exception {
        CreateGameResponse createdGame = createAGame();
        CreateDeckResponse createdDeck = createADeck();
        assignADeck(createdGame.getId(), createdDeck.getId());

        CreatePlayerResponse createdPlayer = createAPlayer(createdGame);
        dealCards(createdGame.getId(), createdPlayer.getId(), 1);

        GetCardsBySuitResponse res = getCardCountBySuit(createdGame.getId());

        Assertions.assertEquals(4, res.getCardsBySuit().size());

        for (CardBySuit cardBySuit : res.getCardsBySuit()) {
            assertThat(cardBySuit.getCount(), anyOf(equalTo(12), equalTo(13)));
        }

        List<CardBySuit> incompleteSuite = res.getCardsBySuit().stream()
                .filter(cbs -> cbs.getCount() == 12)
                .collect(Collectors.toList());
        Assertions.assertEquals(1, incompleteSuite.size());
    }

    @Test
    public void shouldBeAbleToGetCardCountsBySuitAndValue() throws Exception {
        CreateGameResponse createdGame = createAGame();
        CreateDeckResponse createdDeck = createADeck();
        assignADeck(createdGame.getId(), createdDeck.getId());

        CreatePlayerResponse createdPlayer = createAPlayer(createdGame);
        dealCards(createdGame.getId(), createdPlayer.getId(), 1);

        GetCardsBySuitAndValueResponse res = getCardCountBySuitAndValue(createdGame.getId());

        Assertions.assertEquals(51, res.getCardsBySuitAndValue().size());
        for (CardBySuitAndValue cardBySuitAndValue : res.getCardsBySuitAndValue()) {
            assertThat(cardBySuitAndValue.getCount(), equalTo(1));
        }
    }

    private CreateGameResponse createAGame() throws Exception {
        ResultActions ra =  mockMvc.perform(post("/v1/games"))
                .andExpect(status().isCreated());

        MvcResult result = ra.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        return objectMapper.readValue(contentAsString, CreateGameResponse.class);
    }

    private CreateDeckResponse createADeck() throws Exception {
        ResultActions ra =  mockMvc.perform(post("/v1/decks"))
                .andExpect(status().isCreated());

        MvcResult result = ra.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        return objectMapper.readValue(contentAsString, CreateDeckResponse.class);
    }

    private CreatePlayerResponse createAPlayer(CreateGameResponse game) throws Exception {
        ResultActions ra = mockMvc.perform(
                post("/v1/games/" + game.getId() + "/players")
        ).andExpect(status().isCreated());

        MvcResult result = ra.andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        return objectMapper.readValue(contentAsString, CreatePlayerResponse.class);
    }

    private void assignADeck(UUID gameId, UUID deckId) throws Exception{
        DeckAssignmentRequest dar = new DeckAssignmentRequest(deckId);

        mockMvc.perform(
                post("/v1/games/" + gameId + "/deckassignments")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dar))
        ).andExpect(status().isOk());
    }

    private void dealCards(UUID gameId, UUID playerId, int howMany) throws Exception {
        DealerRequest dr = new DealerRequest(playerId, howMany);

        mockMvc.perform(
                post("/v1/games/" + gameId + "/dealer")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dr))
        ).andExpect(status().isOk());
    }

    private GetHandResponse getHand(UUID gameId, UUID playerId) throws Exception {
        ResultActions ra = mockMvc.perform(
                get("/v1/games/" + gameId + "/players/" + playerId + "/hand")
        ).andExpect(status().isOk());

        MvcResult result = ra.andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        return objectMapper.readValue(contentAsString, GetHandResponse.class);
    }

    private GetGamePlayersResponse getGamePlayers(UUID gameId) throws Exception {
        ResultActions ra = mockMvc.perform(
                get("/v1/games/" + gameId + "/players")
        ).andExpect(status().isOk());

        MvcResult result = ra.andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        return objectMapper.readValue(contentAsString, GetGamePlayersResponse.class);
    }

    private GetCardsBySuitResponse getCardCountBySuit(UUID gameId) throws Exception {
        ResultActions ra = mockMvc.perform(
                get("/v1/games/" + gameId + "/gamedeck")
                .accept("application/bysuit+json")
        ).andExpect(status().isOk());

        MvcResult result = ra.andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        return objectMapper.readValue(contentAsString, GetCardsBySuitResponse.class);
    }

    private GetCardsBySuitAndValueResponse getCardCountBySuitAndValue(UUID gameId) throws Exception {
        ResultActions ra = mockMvc.perform(
                get("/v1/games/" + gameId + "/gamedeck")
                        .accept("application/bysuitandvalue+json")
        ).andExpect(status().isOk());

        MvcResult result = ra.andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        return objectMapper.readValue(contentAsString, GetCardsBySuitAndValueResponse.class);
    }
}
