package com.zomaz.web.log.parser.web.apache;

import java.io.File;
import java.nio.file.Path;

/**
 *
 * @author sohai
 */
public class LogFile {

    private Path filename;

    public LogFile(Path filename) {
        this.filename = filename;
    }

    /**
     * Get name of Apache filename
     *
     * @return
     */
    public Path getLogFile() {
        return filename;
    }

    /**
     * Read next line from the log file
     *
     * @return next line
     */
    public String readNextLine() {

        return null;
    }

}
