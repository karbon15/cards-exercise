package name.theberge.cardsexerciseserver.dto;

import lombok.*;
import name.theberge.cardsexerciseserver.model.CardDeck;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class CreateDeckResponse {
    @Getter
    private UUID id;

    public CreateDeckResponse(CardDeck deck) {
        id = deck.getId();
    }

}
