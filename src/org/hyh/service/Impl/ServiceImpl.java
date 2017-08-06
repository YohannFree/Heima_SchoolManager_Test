package org.hyh.service.Impl;

import java.sql.SQLException;
import java.util.List;

import org.hyh.dao.IDao;
import org.hyh.service.BaseService;
import org.hyh.service.IService;;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ServiceImpl<T> extends BaseService<T> implements IService<T> {

	public List<T> findAll() throws Exception {
		return function().findAll();
	}

	@Override
	public Boolean save(T t) throws SQLException, Exception {
		return function().save(t);
	}

	@Override
	public T findById(String id) throws Exception {
		return function().findById(id);
	}

	@Override
	public Boolean change(T t) throws Exception {
		return function().change(t);
	}

	@Override
	public Boolean deleteById(String id) throws Exception {
		return function().deleteById(id);
	}


	public IDao<T> function() throws Exception {
		String string = ("org.hyh.dao.impl." + clazz.getSimpleName().toString()) + "Dao";
		Class clazz = Class.forName(string);
		IDao<T> Dao = (IDao<T>) clazz.newInstance();
		return Dao;
	}

}
