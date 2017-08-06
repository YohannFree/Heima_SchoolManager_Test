package org.hyh.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseService<T> {
	protected Class<T> clazz;

	public BaseService() {
		clazz = getEntityClass();
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {//获取 泛型 对应的类.class
		Type type = getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType pType = (ParameterizedType) type;
			clazz = (Class<T>) pType.getActualTypeArguments()[0];
		}
		return clazz;
	}
}
