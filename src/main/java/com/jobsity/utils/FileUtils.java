package com.jobsity.utils;

import java.util.Optional;

public final class FileUtils {

    private static final String FOUL = "F";

    private FileUtils() {}

    public static boolean isValidFilePath(String path) {
        return path != null && !path.isEmpty() && path.length() <= 255;
    }

    public static boolean hasTxtExtension(String path) {
        Optional<String> extension = Optional.ofNullable(path)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(path.lastIndexOf(".") + 1));
        return extension.map(s -> s.equals("txt")).orElse(false);
    }

    public static boolean hasEmptyValues(String name, String pinsDown) {
        return !name.trim().isEmpty() && !pinsDown.trim().isEmpty();
    }

    public static Integer convertToIntegerOrReturnNull(String pinsDown) {
        int number;
        if (isFoul(pinsDown)) {
            number = 0;
        } else {
            try {
                number = Integer.parseInt(pinsDown);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return number;
    }

    public static boolean isFoul(String string) {
        return (FOUL).equals(string);
    }
}