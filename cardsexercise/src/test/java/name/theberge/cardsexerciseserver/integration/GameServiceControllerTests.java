package name.theberge.cardsexerciseserver.integration;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import name.theberge.cardsexerciseserver.dto.CreateDeckResponse;
import name.theberge.cardsexerciseserver.dto.CreateGameResponse;

import name.theberge.cardsexerciseserver.dto.DeckAssignmentRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

        ResultActions ra =  mockMvc.perform(delete("/v1/game/" + createResponse.getId()))
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

        DeckAssignmentRequest dar = new DeckAssignmentRequest(createdDeck.getId());

        ResultActions ra =  mockMvc.perform(
                post("/v1/game/" + createdGame.getId() + "/deckassignment")
                        .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dar))
        ).andExpect(status().isOk());
    }

    private CreateGameResponse createAGame() throws Exception {
        ResultActions ra =  mockMvc.perform(post("/v1/game"))
                .andExpect(status().isCreated());

        MvcResult result = ra.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        return objectMapper.readValue(contentAsString, CreateGameResponse.class);
    }

    private CreateDeckResponse createADeck() throws Exception {
        ResultActions ra =  mockMvc.perform(post("/v1/deck"))
                .andExpect(status().isCreated());

        MvcResult result = ra.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        return objectMapper.readValue(contentAsString, CreateDeckResponse.class);
    }


}
