package name.theberge.cardsexerciseserver.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameServiceException extends RuntimeException {
    public GameServiceException(String msg) {super(msg);}
}
