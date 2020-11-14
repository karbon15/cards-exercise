package name.theberge.cardsexerciseserver.unit.repository.implementation;

import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.GameDeck;
import name.theberge.cardsexerciseserver.unit.repository.GameDeckRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GameDeckRepositoryImpl implements GameDeckRepository {
    @Override
    public GameDeck create(GameDeck gameDeck) {
        return null;
    }

    @Override
    public GameDeck update(GameDeck gameDeck) {
        return null;
    }

    @Override
    public GameDeck getByGame(Game game) {
        return null;
    }
}
