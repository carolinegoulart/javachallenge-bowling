package com.jobsity.file;

import com.jobsity.game.BowlingMatch;

public interface FileReader {
    BowlingMatch readFile(String filePath);
}