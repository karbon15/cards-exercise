package name.theberge.cardsexerciseserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCardsBySuitResponse {

    @Getter
    private List<CardBySuit> cardsBySuit;
}
