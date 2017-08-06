package org.hyh.utils;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

public class ReflectUtils {
	/**
     * 通过字段名从对象或对象的父类中得到字段的值
     * @param object 对象实例
     * @param fieldName 字段名
     * @return 字段对应的值
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
     * @throws Exception
     * @Author : ll. create at 2016年4月14日 上午9:18:19
     */
    @SuppressWarnings("unused")
	public static Object getValue(Object object, String fieldName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException  {
        if (object == null) {
            return null;
        }
        if (StringUtils.isBlank(fieldName)) {
            return null;
        }
        Field field = null;
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(object);
           
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。  
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了  
            
        }

        return null;
    }
}
