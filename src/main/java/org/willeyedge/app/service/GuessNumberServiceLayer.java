package org.willeyedge.app.service;


import org.willeyedge.app.model.Round;
import org.willeyedge.app.model.Game;

import java.util.List;

public interface GuessNumberServiceLayer {

    public Game begin();
    public Round guess(String word, int gameId);
    public List<Game> allGames();
    public Game gameById(int id);
    public List<Round> roundsByGameId(int id);



}
