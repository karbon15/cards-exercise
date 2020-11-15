package name.theberge.cardsexerciseserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GONE)
public class DeckAlreadyUsedException extends GameServiceException {
}
