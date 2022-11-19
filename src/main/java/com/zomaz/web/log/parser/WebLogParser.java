package com.zomaz.web.log.parser;

import com.zomaz.web.log.parser.util.ParseCommandLine;
import com.zomaz.web.log.parser.web.apache.AccessLog;
import java.io.IOException;

/**
 *
 * @author sohai
 */
public class WebLogParser {

    public static void main(String[] args) throws IOException {

        ParseCommandLine.parseArgs(args);

        AccessLog accessLog = new AccessLog(Config.logFilename);
        accessLog.filterWithRegex();
    }

}
