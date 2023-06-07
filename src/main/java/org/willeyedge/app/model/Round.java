package org.willeyedge.app.model;

import java.time.LocalDateTime;

public class Round {

    private int roundId;
    private String guess;
    private LocalDateTime dateTime;
    private int gameId;


    private int partial;
    private int exact;


    public int getPartial() {
        return partial;
    }

    public void setPartial(int partial) {
        this.partial = partial;
    }

    public int getExact() {
        return exact;
    }

    public void setExact(int exact) {
        this.exact = exact;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }



}
