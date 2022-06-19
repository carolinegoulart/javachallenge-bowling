package com.jobsity;

import com.jobsity.console.ConsoleReader;
import com.jobsity.console.impl.ConsoleReaderImpl;
import com.jobsity.file.FileReader;
import com.jobsity.file.impl.FileReaderImpl;
import com.jobsity.game.BowlingMatch;
import com.jobsity.game.PlayerGame;
import com.jobsity.game.impl.PlayerGameImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ConsoleReader consoleReader = new ConsoleReaderImpl();

        List<PlayerGame> playerGames = new ArrayList<PlayerGameImpl>();
        BowlingMatch bowlingMatch = new BowlingMatch(playerGames);

        FileReader fileReader = new FileReaderImpl(bowlingMatch);

        BowlingApplication application = new BowlingApplication(consoleReader, fileReader, bowlingMatch);
        application.execute();
    }
}