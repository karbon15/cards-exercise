package name.theberge.cardsexerciseserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.Player;

import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
public class CreateGameResponse {
    @Getter
    private UUID id;
    @Getter
    private Collection<Player> players;

    public CreateGameResponse(Game game) {
        id = game.getId();
        players = game.getPlayers();
    }

}
