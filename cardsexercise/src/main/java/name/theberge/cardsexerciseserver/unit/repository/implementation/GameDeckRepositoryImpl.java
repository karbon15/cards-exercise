package name.theberge.cardsexerciseserver.unit.repository.implementation;

import name.theberge.cardsexerciseserver.exception.DeckAlreadyExistsException;
import name.theberge.cardsexerciseserver.exception.DeckNotFoundException;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.GameDeck;
import name.theberge.cardsexerciseserver.unit.repository.GameDeckRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class GameDeckRepositoryImpl implements GameDeckRepository {

    Map<UUID, GameDeck> decks = new HashMap<>();

    @Override
    public GameDeck create(GameDeck gameDeck) {
        if (decks.containsKey(gameDeck.getGameId())) {
            throw new DeckAlreadyExistsException();
        }
        decks.put(gameDeck.getGameId(), gameDeck);
        return gameDeck;
    }

    @Override
    public GameDeck update(GameDeck gameDeck) {
        if (!decks.containsKey(gameDeck.getGameId())) {
            throw new DeckNotFoundException();
        }
        return decks.put(gameDeck.getGameId(), gameDeck);
    }

    @Override
    public GameDeck getByGame(UUID gameId) {
        if (!decks.containsKey(gameId)) {
            throw new DeckNotFoundException();
        }
        return decks.get(gameId);
    }
}
