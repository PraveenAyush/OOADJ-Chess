package ooadj.chessmp.controller;

import ooadj.chessmp.repository.ChessGameRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebChessGameController {
    private ChessGameRepository chessGameRepository;

    public WebChessGameController(ChessGameRepository chessGameRepository) {
        this.chessGameRepository = chessGameRepository;
    }

    @GetMapping("/game/{id}")
    public String renderGamePage(@PathVariable String id, Model model) {
        if (chessGameRepository.findAllGameId().contains(Integer.parseInt(id))) {
            model.addAttribute("id", id);
            return "game";
        }
        return "<script>location.replace('/')</script>";
    }

}
