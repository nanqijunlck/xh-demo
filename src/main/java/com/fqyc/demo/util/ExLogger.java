package com.fqyc.demo.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author lck
 * @Date 2022/12/6 16:01
 * @Version 1.0
 * @Desc
 */
public class ExLogger {

    private static final int FIELD_MAX_COUNT = 10;
    private static final String delimiter = "#";
    private static final Logger slf4jLogger = LoggerFactory.getLogger(ExLogger.class);
    private Logger logger;
    private List<String> fields;

    private ExLogger(Logger logger)
    {
        this.logger = logger;
        this.fields = new LinkedList();
    }

    public static ExLogger logger()
    {
        return new ExLogger(slf4jLogger);
    }

    public static ExLogger logger(Class<?> clz)
    {
        return new ExLogger(LoggerFactory.getLogger(clz));
    }

    public static ExLogger logger(String loggerName)
    {
        return new ExLogger(LoggerFactory.getLogger(loggerName));
    }

    public ExLogger field(String value)
    {
        if (this.fields.size() >= 10) {
            return this;
        }
        if (value == null) {
            value = "null";
        }
        value = value.replace("#", "");
        this.fields.add(value);
        return this;
    }

    public void debug(String message, Object... args)
    {
        this.logger.debug(String.format("%s#$$%s", new Object[] { buildFieldsStr(), message }), args);
    }

    public void info(String message, Object... args)
    {
        this.logger.info(String.format("%s#$$%s", new Object[] { buildFieldsStr(), message }), args);
    }

    public void warn(String message, Object... args)
    {
        this.logger.warn(String.format("%s#$$%s", new Object[] { buildFieldsStr(), message }), args);
    }

    public void error(String message, Object... args)
    {
        this.logger.error(String.format("%s#$$%s", new Object[] { buildFieldsStr(), message }), args);
    }

    private String buildFieldsStr()
    {
        return String.join("#", this.fields);
    }
}
