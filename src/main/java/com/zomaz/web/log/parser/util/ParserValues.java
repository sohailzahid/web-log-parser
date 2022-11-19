package com.zomaz.web.log.parser.util;

import com.zomaz.web.log.parser.Config;
import com.zomaz.web.log.parser.web.LogFileType;
import com.zomaz.web.log.parser.web.LogFormatType;
import com.zomaz.web.log.parser.web.ServerType;

/**
 *
 * @author sohai
 */
public class ParserValues {

    public static void setServerType(String server) {

        switch (server) {
            case "apache":
                Config.serverType = ServerType.ApacheHttpd;
                break;
        }
    }

    public static void setLogFileType(String filetype) {

        switch (filetype) {
            case "access":
                Config.logType = LogFileType.ApacheAccess;
                break;
            case "error":
                Config.logType = LogFileType.ApacheError;
                break;
        }
    }

    public static void setLogFormatType(String logformat) {

        switch (logformat) {
            case "combined":
                Config.logFormat = LogFormatType.COMBINED;
                break;
            case "common":
                Config.logFormat = LogFormatType.COMMON;
                break;
            case "ncsa":
                Config.logFormat = LogFormatType.NCSA;
                break;
        }
    }
}
