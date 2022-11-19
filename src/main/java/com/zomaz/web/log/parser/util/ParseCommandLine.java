package com.zomaz.web.log.parser.util;

import com.zomaz.web.log.parser.Config;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * -s apache -file C:\apps\apache\httpd\logs\wp.log -l access -f combined
 *
 * @author sohai
 */
public class ParseCommandLine {
    
    private static final Options options;
    
    static {
        options = new Options();
        
        var option = Option.builder("s")
                .argName("server")
                .longOpt("server")
                .hasArg()
                .desc("Server type, such as Apache or Kafka.")
                .build();
        options.addOption(option);
        
        option = Option.builder("l")
                .argName("logtype")
                .longOpt("logtype")
                .hasArg()
                .desc("LogFile type. Example for Apache web server it could be access or error.")
                .build();
        options.addOption(option);
        
        option = Option.builder("f")
                .argName("logformat")
                .longOpt("logformat")
                .hasArg()
                .desc("Describe log format string.")
                .build();
        options.addOption(option);
        
        option = Option.builder("file")
                .argName("file")
                .longOpt("file")
                .hasArg()
                .desc("Describe log format string.")
                .build();
        options.addOption(option);
        
        option = Option.builder("c")
                .argName("configfile")
                .longOpt("configfile")
                .hasArg()
                .desc("Config file name.")
                .build();
        options.addOption(option);
    }
    
    public ParseCommandLine() {
    }
    
    public static void parseArgs(String[] args) {
        
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        
        if (cmd.hasOption("configfile")) {
            readConfigFile(cmd.getOptionValue("configfile"));
            return;
        }
        if (cmd.hasOption("server")) {
            ParserValues.setServerType(cmd.getOptionValue("server"));
        } else {
            System.exit(1);
        }
        
        if (cmd.hasOption("logtype")) {
            ParserValues.setLogFileType(cmd.getOptionValue("logtype"));
        } else {
            System.exit(1);
        }
        
        if (cmd.hasOption("logformat")) {
            ParserValues.setLogFileType(cmd.getOptionValue("logformat"));
        } else {
            System.exit(1);
        }
        
        if (cmd.hasOption("file")) {
            Config.logFilename = cmd.getOptionValue("file");
        } else {
            System.exit(1);
        }
    }
    
    private static void readConfigFile(String filename) {
        
        try ( InputStream input = new FileInputStream("app.conf")) {
            
            Config.setProperties(input);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
