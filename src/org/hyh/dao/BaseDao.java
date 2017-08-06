package org.hyh.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.dbutils.QueryRunner;
import org.hyh.utils.JDBCUtils;

public class BaseDao<T> {
	protected QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDateSource());

	protected Class<T> clazz;

	public BaseDao() {
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
