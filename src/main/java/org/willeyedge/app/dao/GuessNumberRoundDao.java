package org.willeyedge.app.dao;

import org.willeyedge.app.model.Round;

import java.util.List;

public interface GuessNumberRoundDao {

    Round add(Round round);

    Round getRound(int id);

    List<Round> getAll();

    List<Round> getAllForGame(int gameId);





}
