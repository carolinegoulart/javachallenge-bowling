package com.jobsity.file.impl;

import com.jobsity.game.BowlingMatch;
import com.jobsity.game.PlayerGame;
import com.jobsity.game.impl.PlayerGameImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static com.jobsity.utils.FileUtils.convertToIntegerOrReturnNull;
import static com.jobsity.utils.FileUtils.hasEmptyValues;

public class FileReaderImpl implements com.jobsity.file.FileReader {

    private final BowlingMatch bowlingMatch;
    private BufferedReader bufferedReader;
    private FileReader fileReader;

    public FileReaderImpl(BowlingMatch bowlingMatch) {
        this.bowlingMatch = bowlingMatch;
        this.bufferedReader = null;
    }

    @Override
    public BowlingMatch readFile(String filePath) {
        try {
            this.fileReader = new FileReader(filePath);
            this.bufferedReader = new BufferedReader(fileReader);

            int lineCount = 1;
            for (String line = this.bufferedReader.readLine(); (line != null && !line.trim().isEmpty()); line = this.bufferedReader.readLine()) {

                String[] splitStrings = line.split("\t", -1);
                String name = splitStrings[0];
                if (splitStrings.length != 2) {
                    System.out.println(String.format("Error: Invalid input [line number: %d]. Values (name and number of pins down) should be tab-separated", lineCount));
                    break;
                }
                String pinsDownString = splitStrings[1];
                if (!hasEmptyValues(name, pinsDownString)) {
                    System.out.println(String.format("Error: Invalid input [line number: %d]. Line contains null or empty values", lineCount));
                    break;
                }
                Integer pinsDown = convertToIntegerOrReturnNull(pinsDownString);
                if (pinsDown == null) {
                    System.out.println(String.format("Error: Invalid input [line number: %d]. Invalid number of pins down", lineCount));
                    break;
                }
                if (pinsDown < 0) {
                    System.out.println(String.format("Error: Invalid input [line number: %d]. Negative number of pins down is not allowed", lineCount));
                    break;
                }

                PlayerGame game = bowlingMatch.getGameByPlayerName(name);
                if (game == null) {
                    game = new PlayerGameImpl(name);
                    bowlingMatch.addPlayerGame(game);
                }
                game.roll(pinsDown);

                lineCount++;
            }
            return bowlingMatch;
        } catch (FileNotFoundException e) {
            System.out.println("Error: Sorry, file not found");
            return bowlingMatch;
        } catch (Exception e) {
            System.out.println("Error: Unexpected behavior while reading the file");
            return bowlingMatch;
        } finally {
            if (this.bufferedReader != null) {
                try {
                    this.fileReader.close();
                    this.bufferedReader.close();
                } catch (IOException e) {
                    System.out.println("Error: Unexpected behavior while closing Buffered Reader connection");
                }
            }
        }
    }
}