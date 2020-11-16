package name.theberge.cardsexerciseserver.service;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import name.theberge.cardsexerciseserver.model.Card;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.model.CardFaceValue;
import name.theberge.cardsexerciseserver.model.CardSuit;
import name.theberge.cardsexerciseserver.model.GameDeck;
import org.javatuples.Pair;

public interface GameDeckService {

  GameDeck create(UUID gameId);

  void addACardDeck(GameDeck gameDeck, CardDeck cardDeck);

  Collection<Card> dealCards(UUID gameId, int howMany);

  Map<CardSuit, Integer> getUndealtCardCountBySuit(UUID gameId);

  Map<Pair<CardSuit, CardFaceValue>, Integer> getUndealtCardCountBySuitAndValue(UUID gameId);

  void shuffle(UUID gameId);

  GameDeck getByGameId(UUID gameId);

  void deleteByGameId(UUID gameId);
}
