package com.fqyc.demo.config.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 枚举值验证注解
 * e.g
 * 属性类型为String：
 * - @EnumValue(strValus={"1","2"}, message="xxx")
 * - private String type;
 * <p>
 * 属性类型为Integer:
 * - @EnumValue(intValus={1,2}, message="xxx")
 * - private Integer type;
 * <p>
 * 建议：尽量使用原生注解，如果Integer类型的属性的值是连续的，可以使用{@link Range}标签。String类型可以使用{@link Pattern}
 *
 * @author panyi
 * @date 2020-04-17 9:44
 * @since 1.0
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {EnumValueValidator.class})
public @interface EnumValue {
    // 默认错误消息
    String message() default "值不在有效范围内";

    String[] strValues() default {};

    int[] intValues() default {};

    boolean isRequired() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    //指定多个时使用
    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        EnumValue[] value();
    }
}
