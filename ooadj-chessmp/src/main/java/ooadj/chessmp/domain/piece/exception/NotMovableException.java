package ooadj.chessmp.domain.piece.exception;

public class NotMovableException extends RuntimeException {
    private static final String NOT_MOVABLE_MESSAGE = "Can't move to the position.";

    public NotMovableException() {
        super(NOT_MOVABLE_MESSAGE);
    }

    public NotMovableException(String message) {
        super(message);
    }
}
