package name.theberge.cardsexerciseserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import name.theberge.cardsexerciseserver.model.CardSuite;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardBySuit {
    @Getter
    private CardSuite cardSuite;
    @Getter
    private Integer count;
}
