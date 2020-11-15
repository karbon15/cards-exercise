package name.theberge.cardsexerciseserver.integration;

import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import name.theberge.cardsexerciseserver.dto.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

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
        //TODO: Observe the effects on the future read endpoints
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

        ResultActions ra =  mockMvc.perform(
                post("/v1/games/" + gameId + "/deckassignments")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dar))
        ).andExpect(status().isOk());
    }

    private void dealCards(UUID gameId, UUID playerId, int howMany) throws Exception {
        DealerRequest dr = new DealerRequest(playerId, 5);

        mockMvc.perform(
                post("/v1/games/" + gameId + "/dealer")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dr))
        ).andExpect(status().isOk());
    }

    private GetHandResponse getHand(UUID gameId, UUID playerId) throws Exception {
        DealerRequest dr = new DealerRequest(playerId, 5);

        ResultActions ra = mockMvc.perform(
                get("/v1/games/" + gameId + "/players/" + playerId + "/hand")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dr))
        ).andExpect(status().isOk());

        MvcResult result = ra.andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        return objectMapper.readValue(contentAsString, GetHandResponse.class);
    }




}
