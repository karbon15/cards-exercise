package name.theberge.cardsexerciseserver.service.implementation;

import java.util.UUID;
import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.repository.CardDeckRepository;
import name.theberge.cardsexerciseserver.service.CardDeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardDeckServiceImpl implements CardDeckService {

  @Autowired
  CardDeckRepository cardDeckRepository;


  @Override
  public CardDeck create() {
    CardDeck cardDeck = new CardDeck();
    cardDeckRepository.create(cardDeck);
    return cardDeck;
  }

  @Override
  public CardDeck update(CardDeck cardDeck) {
    cardDeckRepository.update(cardDeck);
    return cardDeck;
  }

  @Override
  public CardDeck getById(UUID deckId) {
    return cardDeckRepository.getById(deckId);
  }
}
