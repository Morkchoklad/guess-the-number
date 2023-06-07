package org.willeyedge.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.willeyedge.app.model.Round;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class GuessNumberRoundDaoImpl implements GuessNumberRoundDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GuessNumberRoundDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Round add(Round round) {
        final String sql = "INSERT INTO round(guess,roundTime,gameId,partialGuess,exactGuess) VALUES(?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, round.getGuess());
            statement.setTimestamp(2, Timestamp.valueOf(round.getDateTime()));
            statement.setInt(3, round.getGameId());
            statement.setInt(4, round.getPartial());
            statement.setInt(5, round.getExact());

            return statement;

        }, keyHolder);

        round.setRoundId(keyHolder.getKey().intValue());

        return round;
    }

    @Override
    public Round getRound(int id) {
        final String sql = "SELECT * FROM round WHERE roundId = ?;";
        return jdbcTemplate.queryForObject(sql, new GuessNumberRoundMapper(),id);
    }

    @Override
    public List<Round> getAll() {
        final String sql = "SELECT * FROM round;";
        return jdbcTemplate.query(sql, new GuessNumberRoundMapper());

    }

    @Override
    public List<Round> getAllForGame(int gameId) {
        final String sql = "SELECT * FROM round WHERE gameId = ?;";
        return jdbcTemplate.query(sql, new GuessNumberRoundMapper(),gameId);
    }
}
