package name.theberge.cardsexerciseserver.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
public class Player {

    @Getter
    @Setter
    private String id;

    @Getter
    private Collection<Card> cards = new ArrayList<>();

    public void receiveACard(Card card) {
        cards.add(card);
    }
}
