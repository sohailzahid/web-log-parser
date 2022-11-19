package com.zomaz.web.log.parser.web;

/**
 *
 * @author sohai
 */
public enum LogFormatType {

    COMMON("%h %l %u %t \"%r\" %>s %b"),
    COMBINED("%h %l %u %t \"%r\" %>s %b \"%{Referer}i\" \"%{User-Agent}i\""),
    NCSA("%h %l %u %t \"%r\" %>s %b \"%{Referer}i\" \"%{User-agent}i\"");

    public final String format;

    private LogFormatType(String format) {
        this.format = format;
    }
}
