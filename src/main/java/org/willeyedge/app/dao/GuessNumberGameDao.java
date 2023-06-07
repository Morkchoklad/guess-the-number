package org.willeyedge.app.dao;

import org.willeyedge.app.model.Game;

import java.util.List;

public interface GuessNumberGameDao {

    Game add(Game game);

    List<Game> getAll();

    Game findById(int id);

    boolean update(Game game);


}
