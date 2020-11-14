package name.theberge.cardsexerciseserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DeckAlreadyExistsException extends GameServiceException {
}
