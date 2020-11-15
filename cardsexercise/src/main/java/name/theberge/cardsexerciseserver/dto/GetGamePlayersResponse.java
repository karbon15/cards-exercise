package name.theberge.cardsexerciseserver.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetGamePlayersResponse {

  @Getter
  private List<PlayerWithScore> players;
}
