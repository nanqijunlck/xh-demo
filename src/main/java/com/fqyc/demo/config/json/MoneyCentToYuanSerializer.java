package com.fqyc.demo.config.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 结算报表-b端-金额处理 分 ——> 元
 */
public class MoneyCentToYuanSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        if (value == null) {
            value = BigDecimal.ZERO;
        }
        jsonGenerator.writeString(serializeMoney(value));
    }

    private static String serializeMoney(BigDecimal value) {
        value = value.divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
        return value.toPlainString();
    }

}
