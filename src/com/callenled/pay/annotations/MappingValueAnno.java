package com.callenled.pay.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Callenld
 * @Date: 18-11-26
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MappingValueAnno {
    /**
     * 原有属性
     * @return
     */
    String fromField() default "";

    /**
     * 默认值
     * @return
     */
    String defaultValue() default "";

}
