package org.willeyedge.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.willeyedge.app.model.Game;
import org.willeyedge.app.model.Round;
import org.willeyedge.app.service.GuessNumberServiceLayer;

import java.util.List;


@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    GuessNumberServiceLayer serviceLayer;



    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public String[] begin(){
        Game game = serviceLayer.begin();
        String[] result = {"" + game.getGameId()};
        return result;
    }


    @PostMapping("/guess")
    public String[] guess(String word, int gameId){
        Round round = serviceLayer.guess(word,gameId);
        String[] result = new String[]{round.getRoundId() + "", round.getDateTime()+ "",
                round.getGameId()+"", "e:"+ round.getExact()+":p:"+ round.getPartial()};
        return result;
    }

    @GetMapping("/game")
    public String[][] game() {

        List<Game> games = serviceLayer.allGames();
        String[][] result = new String[games.size()][4];
        for (int i = 0; i < games.size(); i++) {
            Game game = games.get(i);
            result[i] = new String[]{game.getGameId() + "", game.getWord(), game.isInProgress()+""};
        }
        return result;
    }

    @GetMapping("/game/{gameId}")
    public Game findById(@PathVariable int gameId){
        Game game = serviceLayer.gameById(gameId);
        return game;
    }

    @GetMapping("rounds/{gameId}")
    public String[][] roundsByGameId(@PathVariable int gameId) {
        List<Round> rounds =serviceLayer.roundsByGameId(gameId);
        String[][] result = new String[rounds.size()][4];
        for (int i = 0; i < rounds.size(); i++) {
            Round round = rounds.get(i);
            result[i] = new String[]{round.getRoundId() + "", round.getDateTime()+ "",
                    round.getGameId()+"", "e:"+ round.getExact()+":p:"+ round.getPartial()};
        }
        return result;
    }

}
