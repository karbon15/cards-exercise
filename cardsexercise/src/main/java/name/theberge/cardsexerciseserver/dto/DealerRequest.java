package name.theberge.cardsexerciseserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class DealerRequest {

    @Getter
    private UUID playerId;
    @Getter
    private int count;
}
