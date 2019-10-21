package com.callenled.pay.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Callenld
 * @Date: 18-11-26
 */
public class FieldUtil {

    /**
     * 获取自身或者继承的field
     * @param object
     * @param fieldName
     * @return
     */
    public static Field getDeclaredField(Object object, String fieldName){
        Class<?> clazz = object.getClass() ;
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (Exception e) {
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }
        return null;
    }

    /**
     * 获取自身以及父类的属性
     * @param object
     * @return
     */
    public static List<Field> getDeclaredFields(Object object){
        List<Field> fields = new ArrayList<>();
        Class<?> clazz = object.getClass() ;
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                Field[] arrField = clazz.getDeclaredFields();
                if(arrField.length > 0){
                    fields.addAll(Arrays.asList(arrField));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fields;
    }
}
