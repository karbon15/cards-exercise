package name.theberge.cardsexerciseserver.unit.repository.implementation;

import name.theberge.cardsexerciseserver.exception.DeckAlreadyExistsException;
import name.theberge.cardsexerciseserver.exception.DeckNotFoundException;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.unit.repository.CardDeckRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class CardDeckRepositoryImpl implements CardDeckRepository {

    Map<UUID, CardDeck> decks = new HashMap<>();

    @Override
    public CardDeck create(CardDeck cardDeck) {
        if (decks.containsKey(cardDeck.getId())) {
            throw new DeckAlreadyExistsException();
        }
        decks.put(cardDeck.getId(), cardDeck);
        return cardDeck;
    }

    @Override
    public CardDeck update(CardDeck cardDeck) {
        if (!decks.containsKey(cardDeck.getId())) {
            throw new DeckNotFoundException();
        }
        return decks.put(cardDeck.getId(), cardDeck);
    }

    @Override
    public CardDeck getById(UUID deckId) {
        if (!decks.containsKey(deckId)) {
            throw new DeckNotFoundException();
        }
        return decks.get(deckId);
    }
}
