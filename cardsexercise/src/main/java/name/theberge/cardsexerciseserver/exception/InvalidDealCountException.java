package name.theberge.cardsexerciseserver.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@AllArgsConstructor

public class InvalidDealCountException extends GameServiceException {
  public InvalidDealCountException(String msg) {
    super(msg);
  }
}
