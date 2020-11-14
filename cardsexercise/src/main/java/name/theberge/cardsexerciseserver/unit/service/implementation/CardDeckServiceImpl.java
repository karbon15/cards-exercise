package name.theberge.cardsexerciseserver.unit.service.implementation;

import name.theberge.cardsexerciseserver.model.CardDeck;
import name.theberge.cardsexerciseserver.unit.repository.CardDeckRepository;
import name.theberge.cardsexerciseserver.unit.service.CardDeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardDeckServiceImpl implements CardDeckService {

    @Autowired
    CardDeckRepository cardDeckRepository;


    @Override
    public CardDeck create() {
        return null;
    }

    @Override
    public void delete(CardDeck cardDeck) {

    }
}
