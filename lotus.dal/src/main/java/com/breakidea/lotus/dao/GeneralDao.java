package com.breakidea.lotus.dao;

import java.util.List;

public interface GeneralDao<P, R> {

	List<R> query(P param);

	R queryById(Long id);

	void insert(P param);

	void update(P param);

	void delete(P param);
}
