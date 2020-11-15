package name.theberge.cardsexerciseserver.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetGamePlayersResponse {

    @Getter
    private List<PlayerWithScore> players;
}
