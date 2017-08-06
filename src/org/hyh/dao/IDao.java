package org.hyh.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {

	List<T> findAll() throws SQLException;//查询 所有

	T findById(String id) throws SQLException;//根据id查询

	Boolean save(T t) throws Exception;//保存

	Boolean change(T t) throws Exception;//修改
	
	Boolean deleteById(String id)throws Exception;//根据id删除
}
