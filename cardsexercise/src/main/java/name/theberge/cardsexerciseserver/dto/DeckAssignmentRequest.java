package name.theberge.cardsexerciseserver.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class DeckAssignmentRequest {

    @Getter
    @Setter
    private UUID deckId;
}
