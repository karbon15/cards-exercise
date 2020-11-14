package name.theberge.cardsexerciseserver.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
public class Player {

    @Getter
    private Collection<Card> cards = new ArrayList<>();

    public void receiveACard(Card card) {
        cards.add(card);
    }
}
