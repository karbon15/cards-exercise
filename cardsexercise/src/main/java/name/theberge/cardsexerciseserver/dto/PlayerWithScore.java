package name.theberge.cardsexerciseserver.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
public class PlayerWithScore {
    @Getter
    private UUID id;
    @Getter
    private int score;
}
