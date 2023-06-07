package org.willeyedge.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.willeyedge.app.dao.GuessNumberGameDao;
import org.willeyedge.app.dao.GuessNumberRoundDao;
import org.willeyedge.app.model.Game;
import org.willeyedge.app.model.Round;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class GuessNumberServiceLayerImpl implements GuessNumberServiceLayer {

    class RoundComparator implements Comparator<Round> {


        @Override
        public int compare(Round o1, Round o2) {
            return o1.getDateTime().compareTo(o2.getDateTime());
        }
    }

    private GuessNumberGameDao gameDao;
    private GuessNumberRoundDao roundDao;

    @Autowired
    public GuessNumberServiceLayerImpl(GuessNumberGameDao gameDao, GuessNumberRoundDao roundDao){
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }

    @Override
    public Game begin() {
        Game game = new Game();
        game.setWord(createRandomString());
        return gameDao.add(game);
    }

    @Override
    public Round guess(String word, int gameId) {
        Game game = gameDao.findById(gameId);
        String answer = game.getWord();

        int partial = 0;
        int exact = 0;

        for (int i = 0; i < 4; i++) {
            if (answer.charAt(i) == Character.toUpperCase(word.charAt(i))){
                exact++;
            } else if (answer.indexOf(Character.toUpperCase(word.charAt(i))) > -1){
                partial++;
            }
        }
        if (exact == 4){
            game.setInProgress(false);
            gameDao.update(game);
        }
        Round round = new Round();
        round.setGuess(word);
        round.setGameId(gameId);
        round.setDateTime(LocalDateTime.now());
        round.setExact(exact);
        round.setPartial(partial);
        return roundDao.add(round);
    }

    @Override
    public List<Game> allGames() {
        List<Game> games = gameDao.getAll();
        for (Game game : games) {
            if (game.isInProgress()){
                game.setWord("");
            }
        }
        return games;
    }

    @Override
    public Game gameById(int id) {
        Game game = gameDao.findById(id);
        if (game.isInProgress()){
            game.setWord("");
        }
        return game;
    }

    @Override
    public List<Round> roundsByGameId(int id) {
        List<Round> rounds = roundDao.getAllForGame(id);
        rounds.sort(new RoundComparator());
        return rounds;
    }



    String createRandomString(){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


        String result = "";

        for (int i = 0; i < 4; i++) {
            int index = (int) (Math.random()* alphabet.length());
            char toAdd = alphabet.charAt(index);
            if (result.indexOf(toAdd) == -1){
                result += toAdd;
            } else {
                i--;
            }
        }

        return result;
    }
}
