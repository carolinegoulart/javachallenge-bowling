package com.jobsity.game;

import java.util.List;

public interface PlayerGame {
    void roll(int pinsDown);
    int score();
    List<Integer> getScores();
    List<Integer> getRolls();
    String getPlayerName();
}