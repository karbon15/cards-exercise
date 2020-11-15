package name.theberge.cardsexerciseserver.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Player {

    @Getter
    @Setter
    private UUID id;

    @Getter
    private Collection<Card> cards = new ArrayList<>();

    public Player() {
        id = UUID.randomUUID();
    }

    public void receiveCards(Collection<Card> cardsToAdd) {
        cards.addAll(cardsToAdd);
    }
}
