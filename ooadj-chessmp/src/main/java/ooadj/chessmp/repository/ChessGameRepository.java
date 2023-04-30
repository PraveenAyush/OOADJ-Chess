package ooadj.chessmp.repository;

import java.util.List;

import ooadj.chessmp.entity.ChessGameEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChessGameRepository extends CrudRepository<ChessGameEntity, Integer> {

    @Query("SELECT id FROM chess_game")
    List<Integer> findAllGameId();
}
