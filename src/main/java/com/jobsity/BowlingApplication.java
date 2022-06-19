package com.jobsity;

import com.jobsity.console.ConsoleReader;
import com.jobsity.file.FileReader;
import com.jobsity.game.BowlingMatch;

public class BowlingApplication {

    private final ConsoleReader consoleReader;
    private final FileReader fileReader;
    private BowlingMatch bowlingMatch;

    public BowlingApplication(ConsoleReader consoleReader,
                              FileReader fileReader,
                              BowlingMatch bowlingMatch) {
        this.fileReader = fileReader;
        this.consoleReader = consoleReader;
        this.bowlingMatch = bowlingMatch;
    }

    public void execute() {
        System.out.println("Welcome to the bowling score application!");

        boolean loadingSuccess = false;
        do {
            String input = consoleReader.readInputFilePath();
            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Thank you for using the bowling score application!");
                System.exit(0);
            }
            bowlingMatch = fileReader.readFile(input);
            if (!bowlingMatch.exists()) {
                continue;
            }
            loadingSuccess = true;
        } while (!loadingSuccess);


        System.out.println("Thank you for using the bowling score application!");
    }
}