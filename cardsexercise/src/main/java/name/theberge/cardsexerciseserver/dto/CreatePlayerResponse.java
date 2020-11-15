package name.theberge.cardsexerciseserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.Player;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class CreatePlayerResponse {
    @Getter
    @Setter
    private UUID id;

    public CreatePlayerResponse(Player player) {
        id = player.getId();
    }
}
