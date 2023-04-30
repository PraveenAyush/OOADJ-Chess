package ooadj.chessmp.entity;

import ooadj.chessmp.domain.game.ChessGame;
import ooadj.chessmp.domain.game.state.StateFactory;
import ooadj.chessmp.dto.BoardDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("chess_game")
public class ChessGameEntity {

    @Id
    private int id;
    private String state;
    private String board;
    private String turn;

    public ChessGameEntity() {
    }

    public ChessGameEntity(ChessGame chessGame) {
        update(chessGame);
    }

    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getBoard() {
        return board;
    }

    public String getTurn() {
        return turn;
    }

    public void update(ChessGame chessGame) {
        this.state = chessGame.getState().toString();
        this.board = String.join("", new BoardDto(chessGame.board()).getBoard());
        this.turn = String.valueOf(chessGame.turn());
    }

    public ChessGame toModel() {
        return ChessGame.of(StateFactory.valueOf(state).create(board, turn));
    }

}
