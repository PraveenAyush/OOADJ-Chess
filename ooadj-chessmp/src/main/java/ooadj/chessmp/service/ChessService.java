package ooadj.chessmp.service;

import java.util.NoSuchElementException;

import ooadj.chessmp.domain.game.ChessGame;
import ooadj.chessmp.domain.game.exception.InvalidTurnException;
import ooadj.chessmp.domain.game.state.Ready;
import ooadj.chessmp.domain.piece.Position;
import ooadj.chessmp.entity.ChessGameEntity;
import org.springframework.stereotype.Service;

import ooadj.chessmp.domain.piece.exception.NotMovableException;
import ooadj.chessmp.dto.BoardDto;
import ooadj.chessmp.dto.ChessGameDto;
import ooadj.chessmp.dto.ResponseDto;
import ooadj.chessmp.dto.StatusDto;
import ooadj.chessmp.dto.TurnDto;
import ooadj.chessmp.repository.ChessGameRepository;

@Service
public class ChessService {
    public static final String GAME_NOT_EXIST_MESSAGE = "Game doesn't exist.";
    private final ChessGameRepository chessGameRepository;

    public ChessService(ChessGameRepository chessGameRepository) {
        this.chessGameRepository = chessGameRepository;
    }

    public ResponseDto createChessRoom() {
        ChessGame chessGame = ChessGame.of(new Ready());
        chessGame.start();
        ChessGameEntity chessGameEntity = new ChessGameEntity(chessGame);
        ChessGameEntity savedChessGameEntity = chessGameRepository.save(chessGameEntity);
        return new ResponseDto(ResponseDto.SUCCESS, savedChessGameEntity.getId());
    }

    public ResponseDto restartGame(int chessGameId) {
        ChessGame newChessGame = ChessGame.of(new Ready());
        newChessGame.start();

        ChessGameEntity chessGameEntity = chessGameRepository.findById(chessGameId)
            .orElseThrow(() -> new NoSuchElementException(GAME_NOT_EXIST_MESSAGE));
        chessGameEntity.update(newChessGame);
        chessGameRepository.save(chessGameEntity);
        return new ResponseDto(ResponseDto.SUCCESS, chessGameId);

    }

    public ResponseDto movePiece(int chessGameId, Position sourcePosition, Position targetPosition) {
        ChessGameEntity chessGameEntity = chessGameRepository.findById(chessGameId)
            .orElseThrow(() -> new NoSuchElementException(GAME_NOT_EXIST_MESSAGE));
        ChessGame chessGame = chessGameEntity.toModel();
        try {
            chessGame.move(sourcePosition, targetPosition);
            chessGameEntity.update(chessGame);
            chessGameRepository.save(chessGameEntity);
            return responseChessGame(chessGame);
        } catch (NotMovableException | IllegalArgumentException e) {
            return new ResponseDto(ResponseDto.FAIL, "Invalid Move");
        } catch (InvalidTurnException e) {
            return new ResponseDto(ResponseDto.FAIL, chessGame.turn().getColor() + " turn");
        }
    }

    public ResponseDto getChessGameById(int chessGameId) {
        ChessGameEntity chessGameEntity = chessGameRepository.findById(chessGameId)
            .orElseThrow(() -> new NoSuchElementException(GAME_NOT_EXIST_MESSAGE));
        ChessGame chessGame = chessGameEntity.toModel();
        return responseChessGame(chessGame);
    }

    public ResponseDto getGameListId() {
        return new ResponseDto(ResponseDto.SUCCESS, chessGameRepository.findAllGameId());
    }

    private static ResponseDto responseChessGame(ChessGame chessGame) {
        return new ResponseDto(ResponseDto.SUCCESS,
            new ChessGameDto(new BoardDto(chessGame.board()), new TurnDto(chessGame.turn()),
                new StatusDto(chessGame.status().getWhiteScore(), chessGame.status().getBlackScore(),
                    chessGame.status().getWinner()), chessGame.isFinished()));
    }
}
