package com.jobsity.console.impl;

import com.jobsity.console.ConsoleReader;
import com.jobsity.utils.FileUtils;

import java.util.Scanner;

public class ConsoleReaderImpl implements ConsoleReader {

    private final Scanner scanner;

    public ConsoleReaderImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readInputFilePath() {
        System.out.println("Please, input a file path (.txt extension), or type EXIT and press Enter to quit the program: ");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("exit")) {
            return input;
        }
        if (!FileUtils.isValidFilePath(input)) {
            System.out.println("Error: Invalid file path");
            return null;
        }
        if (!FileUtils.hasTxtExtension(input)) {
            System.out.println("Error: Invalid file extension");
            return null;
        }
        return input;
    }
}