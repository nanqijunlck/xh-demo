package com.fqyc.demo.config.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 客户端-C端-金额处理
 */
public class MoneyNormalSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        if (value == null) {
            return;
        }
        jsonGenerator.writeString(serializeMoney(value));
    }

    private static String serializeMoney(BigDecimal value) {
        value = value.setScale(2, BigDecimal.ROUND_HALF_UP);
        return value.toPlainString();
    }

}
