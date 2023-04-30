package ooadj.chessmp.dto;

import ooadj.chessmp.domain.game.Turn;

public class TurnDto {
    private Turn turn;

    public TurnDto(Turn turn) {
        this.turn = turn;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }
}
