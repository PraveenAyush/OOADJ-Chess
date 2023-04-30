package ooadj.chessmp.domain.game.exception;

public class InvalidTurnException extends RuntimeException {
    private static final String INVALID_TURN_MESSAGE = "Opponent's turn.";

    public InvalidTurnException() {
        super(INVALID_TURN_MESSAGE);
    }
}
