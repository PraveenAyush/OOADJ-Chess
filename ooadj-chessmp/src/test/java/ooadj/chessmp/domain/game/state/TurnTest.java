package ooadj.chessmp.domain.game.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ooadj.chessmp.domain.game.Turn;
import ooadj.chessmp.domain.piece.Color;

class TurnTest {
    @Test
    @DisplayName("새로운 턴 반환")
    void next() {
        assertThat(Turn.from(Color.WHITE).next()).isEqualTo(Turn.from(Color.BLACK));
    }
}
