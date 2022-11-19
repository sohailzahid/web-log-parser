package com.zomaz.web.log.parser.web.apache;

import com.zomaz.web.log.parser.Config;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 *
 * @author sohai
 */
public class AccessLog {

    private final LogFile logfile;

    public AccessLog(String filename) {
        this.logfile = new LogFile(Path.of(filename));
    }

    public void parse() throws IOException {

        String regString = Config.props.getProperty("search-expression");
        final Pattern pattern = Pattern.compile(regString);

        Integer count = 0;
        final List<String> lines = new ArrayList<>();
        try ( Stream<String> stream = Files.lines(logfile.getLogFile())) {

            stream.forEach(line -> {

                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {

                    System.out.println(line);
                    lines.add(line);
                }
            });
        }
    }

    private final String COMMON_LOG_FORMAT = "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+) (\\S+)\\s*(\\S+)?\\s*\" (\\d{3}) (\\S+)";

    public void filterWithRegex() throws IOException {

        String regString = Config.props.getProperty("search-expression");
        final Pattern linePattern = Pattern.compile(regString); // For filtering.
        final Pattern columnPattern = Pattern.compile(COMMON_LOG_FORMAT, Pattern.MULTILINE);

        try ( Stream<String> stream = Files.lines(logfile.getLogFile())) {

            var countIP = new HashMap<String, Integer>();
            stream.forEach(line -> {
                Matcher matcher = columnPattern.matcher(line);

                while (matcher.find()) {

                    String IP = matcher.group(1);
                    String Response = matcher.group(8);
                    int response = Integer.parseInt(Response);

                    // Inserting the IP addresses in the HashMap and maintaining the frequency for each HTTP 200 code.
                    if (response == 200) {
                        if (countIP.containsKey(IP)) {
                            countIP.put(IP, countIP.get(IP) + 1);
                        } else {
                            countIP.put(IP, 1);
                        }
                    }
                }
            });

            for (Map.Entry entry : countIP.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }
}
