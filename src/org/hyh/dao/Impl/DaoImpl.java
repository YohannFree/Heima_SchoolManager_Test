package org.hyh.dao.Impl;

import java.lang.reflect.Field;
//import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.hyh.dao.BaseDao;
import org.hyh.dao.IDao;
import org.hyh.utils.ID;
import org.hyh.utils.ReflectUtils;
//import org.hyh.utils.TYPEUtils;

public class DaoImpl<T> extends BaseDao<T> implements IDao<T> {
	public List<T> findAll() throws SQLException {
		String sql = "SELECT * FROM " + clazz.getSimpleName() + ";";
		System.out.println(sql);
		List<T> tList = queryRunner.query(sql, new BeanListHandler<T>(clazz));
		return tList;
	}

	// @SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public Boolean save(T t) throws Exception {
		String string = "";
		int count = 0;
		List<Object> list = new ArrayList<>();
		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			if (!field.getType().isPrimitive()) {// 基本类型
				count++;
				// if (TYPEUtils.TypeUtils(field)) {
				// String fieldName = field.getType().getName();
				// String[] strings = fieldName.split("\\.");
				// System.out.println("==================");
				// System.out.println(fieldName);
				// Class cls = Class.forName(fieldName);
				// Field[] clsFields = cls.getDeclaredFields();
				// Method method = clazz.getMethod("get" + cls.getSimpleName());
				// Object invoke = method.invoke(t);
				// Object newInstance = cls.newInstance();
				// newInstance = invoke;
				// for (Field field2 : clsFields) {
				// // Object object = ReflectUtils.getValue(newInstance,
				// field2.getName().toString());
				// //list.add(object);
				// }
				// // for (Field field2 : clsFields) {
				// // Object object = ReflectUtils.getValue(cls., string2);
				// // }
				// System.out.println("=============");
				// } else {
				String string2 = field.getName().toString();
				System.out.println(string2);
				Object object = ReflectUtils.getValue(t, string2);
				list.add(object);
				// }
			}

		}
		// System.out.println("__" + count);
		// Object[] objects = list.toArray();
		// for (Object object : objects) {
		// System.out.println(object);
		// }

		for (int i = 0; i < count; i++) {
			if (i == count - 1) {
				string += "?";
			} else {
				string += "?,";
			}
		}

		String sql = "INSERT INTO " + clazz.getSimpleName() + " VALUES(" + string + ");";

		Object[] objects = list.toArray();
		return queryRunner.update(sql, objects) > 0;
		// return null;
	}

	@Override
	public T findById(String id) throws SQLException {
		Field[] FieldsId = clazz.getDeclaredFields();
		String idAnnotation = null;
		for (Field field : FieldsId) {
			if (field.getAnnotation(ID.class) != null) {
				idAnnotation = field.getAnnotation(ID.class).value();
			}
		}
		String sql = "SELECT * FROM " + clazz.getSimpleName() + " WHERE " + idAnnotation + " = '" + id + "';";
		return queryRunner.query(sql, new BeanHandler<T>(clazz));
	}

	@Override
	public Boolean change(T t) throws Exception {
		Field[] Fields = clazz.getDeclaredFields();
		String string = "";
		String idAnnotation = null;
		Object id = null;
		int count = 0;
		Map<String, Object> map = new HashMap<>();
		for (Field field : Fields) {
			if (!field.getType().isPrimitive()) {
				if (field.getAnnotation(ID.class) != null) {
					idAnnotation = field.getAnnotation(ID.class).value();
					id = ReflectUtils.getValue(t, idAnnotation);
				} else {
					String string2 = field.getName().toString();
					Object object = ReflectUtils.getValue(t, string2);
					map.put(string2, object);
				}
			}
		}
		Set<Entry<String, Object>> entrySet = map.entrySet();
		Iterator<Entry<String, Object>> iterator = entrySet.iterator();
		List<Object> list = new ArrayList<>();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			count++;
			if (count == entrySet.size()) {
				string += entry.getKey() + "= ? " + "WHERE ";
			} else {
				string += entry.getKey() + "= ? , ";
			}
			list.add(entry.getValue());
		}
		list.add(id);
		System.out.println(list);
		String sql = "UPDATE " + clazz.getSimpleName() + " SET " + string + idAnnotation + "= ?" + ";";
		System.out.println(sql);
		Object[] objects = list.toArray();
		return queryRunner.update(sql, objects) > 0;
		// return null;
	}

	@Override
	public Boolean deleteById(String id) throws Exception {
		Field[] FieldsId = clazz.getDeclaredFields();
		String idAnnotation = null;
		for (Field field : FieldsId) {
			if (field.getAnnotation(ID.class) != null) {
				idAnnotation = field.getAnnotation(ID.class).value();
			}
		}
		String sql = "DELETE FROM " + clazz.getSimpleName() + " WHERE " + idAnnotation + " = ?;";

		return queryRunner.update(sql, id) > 0;
	}

}
