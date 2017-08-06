package org.hyh.service;

import java.util.List;

public interface IService<T> {

	List<T> findAll() throws Exception;// 查询所有

	T findById(String id) throws Exception;//根据id查询

	Boolean save(T t) throws Exception;//保存

	Boolean change(T t) throws Exception;//修改
	
	Boolean deleteById(String id)throws Exception;//根据id删除
}
