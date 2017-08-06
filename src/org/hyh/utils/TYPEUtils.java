package org.hyh.utils;

import java.lang.reflect.Field;

public class TYPEUtils {
	static String[] types = { "java.util.Date","java.lang.Integer", "java.lang.Double", "java.lang.Float", "java.lang.Long",
			"java.lang.Short", "java.lang.Byte", "java.lang.Boolean", "java.lang.Character", "java.lang.String", "int",
			"double", "long", "short", "byte", "boolean", "char", "float" };

	public static boolean TypeUtils(Field field) {
		for (String string : types) {
			if (string.equals(field.getType().getName())) {
				return false;
			}
		}
		return true;
	}
}
