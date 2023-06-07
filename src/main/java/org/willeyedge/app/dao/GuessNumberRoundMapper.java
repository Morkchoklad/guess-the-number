package org.willeyedge.app.dao;

import org.springframework.jdbc.core.RowMapper;
import org.willeyedge.app.model.Round;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GuessNumberRoundMapper implements RowMapper<Round> {

    @Override
    public Round mapRow(ResultSet resultSet, int i) throws SQLException {
        Round round = new Round();
        round.setRoundId(resultSet.getInt("roundId"));
        round.setGuess(resultSet.getString("guess"));
        round.setDateTime(resultSet.getTimestamp("roundTime").toLocalDateTime());
        round.setGameId(resultSet.getInt("gameId"));
        round.setPartial(resultSet.getInt("partialGuess"));
        round.setExact(resultSet.getInt("exactGuess"));
        return round;
    }
}
