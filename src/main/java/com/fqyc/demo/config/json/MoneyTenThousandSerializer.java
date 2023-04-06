package com.fqyc.demo.config.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 客户端-C端-金额处理
 */
public class MoneyTenThousandSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        if (value == null) {
            return;
        }
        jsonGenerator.writeString(serializeMoney(value));
    }

    private static String serializeMoney(BigDecimal value) {
        BigDecimal tenThousand = new BigDecimal("9999.99");
        if (value.compareTo(tenThousand) <= 0) {
            value = value.setScale(2, BigDecimal.ROUND_HALF_UP);
            return value.toPlainString();
        } else {
            value = value.divide(tenThousand, 2, BigDecimal.ROUND_HALF_UP);
        }
        return String.format("%s万", value.toPlainString());
    }

}
