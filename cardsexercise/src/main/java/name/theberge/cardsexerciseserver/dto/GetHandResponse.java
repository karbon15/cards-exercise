package name.theberge.cardsexerciseserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import name.theberge.cardsexerciseserver.model.Card;

import java.util.Collection;

@NoArgsConstructor
public class GetHandResponse {

    @Getter
    private Collection<Card> cards;

    public GetHandResponse(Collection<Card> inputCards) {
        cards = inputCards;
    }

}
