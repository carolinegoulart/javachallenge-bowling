package com.jobsity.file.impl;

import com.jobsity.game.BowlingMatch;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class FileReaderImplTest {

    FileReaderImpl fileReader;
    BowlingMatch match;

    private static String currentSystemPath;

    @BeforeClass
    public static void setUpAll() {
        Path relativePath = Paths.get("");
        String absolutePath = relativePath.toAbsolutePath().toString();

        StringBuilder builder = new StringBuilder(absolutePath);
        currentSystemPath = builder.append("/src/test/resources").toString();
    }

    @Before
    public void setUp() {
        fileReader = new FileReaderImpl();
        match = new BowlingMatch();
    }

    @Test
    public void shouldReadFileSuccessfully() {
        String testPath = currentSystemPath + "/positive/scores.txt";
        match = fileReader.readFile(testPath);

        assertEquals(2, match.getPlayerGames().size());
        assertNotNull(match.getGameByPlayerName("Jeff"));
        assertEquals(17, match.getGameByPlayerName("Jeff").getRolls().size());

        assertNotNull(match.getGameByPlayerName("John"));
        assertEquals(18, match.getGameByPlayerName("John").getRolls().size());
    }
}