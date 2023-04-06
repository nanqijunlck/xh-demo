package com.fqyc.demo.config.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class IntegerDaySerializer extends JsonSerializer<Integer> {

    @Override
    public void serialize(Integer value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        String val = value.toString();
        if (val.length() >= 8) {
            String dayString = String.format("%s-%s-%s", val.substring(0, 4), val.substring(4, 6), val.substring(6, 8));
            jsonGenerator.writeString(dayString);
        } else {
            jsonGenerator.writeString(val);
        }
    }

}
