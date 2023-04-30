package ooadj.chessmp.domain.game.state;

import ooadj.chessmp.domain.game.Board;
import ooadj.chessmp.domain.game.Status;
import ooadj.chessmp.domain.game.Turn;
import ooadj.chessmp.domain.piece.Position;

public interface State {
    State start();

    State end();

    State move(Position source, Position target);

    Board board();

    Turn turn();

    Status status();

    boolean isFinished();
}
