package com.jobsity.game;

import java.util.List;

public class BowlingMatch {
    private final List<PlayerGame> games;

    public BowlingMatch(List<PlayerGame> games) {
        this.games = games;
    }

    public void addPlayerGame(PlayerGame game) {
        games.add(game);
    }

    public PlayerGame getGameByPlayerName(String name) {
        return games.stream().filter(game -> game.getPlayerName().equals(name)).findFirst().orElse(null);
    }

    public List<PlayerGame> getPlayerGames() {
        return this.games;
    }

    public boolean exists() {
        return !games.isEmpty();
    }
}