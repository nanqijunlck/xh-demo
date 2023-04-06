package com.fqyc.demo.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author xieyingchao
 */
public class ExceptionLogUtil {
    public static String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }
}
