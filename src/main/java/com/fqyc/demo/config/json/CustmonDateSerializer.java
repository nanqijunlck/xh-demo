package com.fqyc.demo.config.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fqyc.demo.constants.GlobalConstants;
import com.fqyc.demo.util.DateUtil;

import java.io.IOException;
import java.util.Date;

/**
 * @author lck
 * @date 2020-04-23 15:09
 * @since 1.0
 */
public class CustmonDateSerializer extends DateSerializer {
    @Override
    public void serialize(Date value, JsonGenerator g, SerializerProvider provider) throws IOException {
        g.writeString(DateUtil.format(value, GlobalConstants.DateFormat.CN_DAY));
    }
}
