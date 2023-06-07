package org.willeyedge.app.dao;

import org.springframework.jdbc.core.RowMapper;
import org.willeyedge.app.model.Game;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GuessNumberGameMapper implements RowMapper<Game> {
    @Override
    public Game mapRow(ResultSet resultSet, int i) throws SQLException {
        Game game = new Game();
        game.setGameId(resultSet.getInt("gameId"));
        game.setWord(resultSet.getString("word"));
        game.setInProgress(resultSet.getBoolean("inProgress"));
        return game;
    }
}
