package com.jobsity.game.impl;

import com.jobsity.game.PlayerGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerGameImpl implements PlayerGame {
    private final String playerName;
    private final List<Integer> rolls;
    private final List<Integer> scores;

    public PlayerGameImpl(String playerName) {
        this.playerName = playerName;
        this.rolls = new ArrayList<>();
        this.scores = new ArrayList<>();
    }

    @Override
    public void roll(int pinsDown) {
        rolls.add(pinsDown);
    }

    @Override
    public int score() {
        int score = 0;
        int cursor = 0;

        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(cursor)) {
                score += 10 + rolls.get(cursor + 1) + rolls.get(cursor + 2);
                cursor++;
            } else if (isSpare(cursor)) {
                score += 10 + rolls.get(cursor + 2);
                cursor += 2;
            } else {
                score += rolls.get(cursor) + rolls.get(cursor + 1);
                cursor += 2;
            }
            scores.add(score);
        }
        return score;
    }

    private boolean isSpare(int cursor) {
        return rolls.get(cursor) + rolls.get(cursor + 1) == 10;
    }

    private boolean isStrike(int cursor) {
        return rolls.get(cursor) == 10;
    }

    @Override
    public List<Integer> getScores() {
        return this.scores;
    }

    @Override
    public String getPlayerName() {
        return this.playerName;
    }

    @Override
    public List<Integer> getRolls() {
        return this.rolls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerGameImpl that = (PlayerGameImpl) o;
        return getPlayerName().equals(that.getPlayerName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerName());
    }
}