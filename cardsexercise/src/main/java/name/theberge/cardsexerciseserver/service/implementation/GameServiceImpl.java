package name.theberge.cardsexerciseserver.service.implementation;

import name.theberge.cardsexerciseserver.model.Card;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.model.Game;
import name.theberge.cardsexerciseserver.model.Player;
import name.theberge.cardsexerciseserver.service.CardDeckService;
import name.theberge.cardsexerciseserver.service.GameDeckService;
import name.theberge.cardsexerciseserver.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GameServiceImpl implements GameService {

    private GameDeckService gameDeckService;
    private CardDeckService cardDeckService;

    @Autowired
    public GameServiceImpl(GameDeckService gds, CardDeckService cds) {
        gameDeckService = gds;
        cardDeckService = cds;
    }

    @Override
    public Game create() {
        return null;
    }

    @Override
    public void delete(Game game) {

    }

    @Override
    public void addPlayer(Game game, Player player) {

    }

    @Override
    public void removePlayer(Game game, Player player) {

    }

    @Override
    public Collection<Card> getCardsForPlayer(Game game, Player player) {
        return null;
    }

    @Override
    public Collection<Player> getPlayers(Game game) {
        return null;
    }

    @Override
    public void addACardDeck(CardDeck cardDeck) {

    }

    @Override
    public Card dealAcard(Game game, Player player) {
        return null;
    }

    @Override
    public int getUndealtCardCount(Game game) {
        return 0;
    }

    @Override
    public int getUndealtCardCountBySuitAndValue(Game game) {
        return 0;
    }

    @Override
    public void shuffle(Game game) {

    }
}
