package name.theberge.cardsexerciseserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCardsBySuitAndValueResponse {
    @Getter
    private List<CardBySuitAndValue> cardsBySuitAndValue;
}
