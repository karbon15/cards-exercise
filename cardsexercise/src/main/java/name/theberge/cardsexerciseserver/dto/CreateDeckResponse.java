package name.theberge.cardsexerciseserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import name.theberge.cardsexerciseserver.model.CardDeck;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class CreateDeckResponse {
    @Getter
    @Setter
    private UUID id;

    public CreateDeckResponse(CardDeck deck) {
        id = deck.getId();
    }

}
