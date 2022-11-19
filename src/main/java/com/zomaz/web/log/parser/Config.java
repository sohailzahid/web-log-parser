package com.zomaz.web.log.parser;

import com.zomaz.web.log.parser.util.ParserValues;
import com.zomaz.web.log.parser.web.LogFileType;
import com.zomaz.web.log.parser.web.LogFormatType;
import com.zomaz.web.log.parser.web.ServerType;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sohai
 */
public class Config {

    public static String logFilename;
    public static ServerType serverType;
    public static LogFileType logType;
    public static LogFormatType logFormat;
    public static Properties props;

    public static void setProperties(InputStream is) {

        props = new Properties();

        try {
            Config.props.load(is);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }

        logFilename = props.getProperty("logfilename");
        ParserValues.setLogFileType(props.getProperty("logtype"));
        ParserValues.setLogFormatType(props.getProperty("logformat"));
        ParserValues.setServerType(props.getProperty("server"));
    }
}
