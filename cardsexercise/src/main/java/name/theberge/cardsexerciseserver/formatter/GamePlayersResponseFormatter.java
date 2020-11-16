package name.theberge.cardsexerciseserver.formatter;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import name.theberge.cardsexerciseserver.dto.GetGamePlayersResponse;
import name.theberge.cardsexerciseserver.dto.PlayerWithScore;
import name.theberge.cardsexerciseserver.model.Player;

public class GamePlayersResponseFormatter {
  public static GetGamePlayersResponse toGamePlayersResponse(Collection<Player> players) {

    return GetGamePlayersResponse.builder().players(
        players.stream()
            .map(player ->
                PlayerWithScore.builder()
                    .id(player.getId())
                    .score(getPlayerScore(player))
                    .build()
            )
            .sorted(Comparator.comparingInt(PlayerWithScore::getScore).reversed())
            .collect(Collectors.toList())
    ).build();
  }

  private static int getPlayerScore(Player player) {
    return player.getCards().stream()
        .map(card -> card.getFaceValue().getValue())
        .collect(Collectors.summingInt(Integer::intValue));
  }
}
