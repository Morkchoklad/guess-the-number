package org.willeyedge.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.willeyedge.app.model.Game;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.util.List;

@Repository
public class GuessNumberGameDaoImpl implements GuessNumberGameDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GuessNumberGameDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game add(Game game) {

        final String sql = "INSERT INTO game(word) VALUES(?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getWord());

            return statement;

        }, keyHolder);

        game.setGameId(keyHolder.getKey().intValue());

        return game;
    }

    @Override
    public List<Game> getAll() {
        final String sql = "SELECT * FROM game;";
        return jdbcTemplate.query(sql, new GuessNumberGameMapper());
    }

    @Override
    public Game findById(int id) {
        final String sql = "SELECT * FROM game WHERE gameId = ?;";
        return jdbcTemplate.queryForObject(sql, new GuessNumberGameMapper(),id);
    }

    @Override
    public boolean update(Game game) {
        final String sql = "UPDATE game SET" +
                " word = ?," +
                " inProgress = ?" +
                " WHERE gameId = ?";

        return jdbcTemplate.update(sql, game.getWord(),game.isInProgress(),game.getGameId()) > 0;
    }
}
