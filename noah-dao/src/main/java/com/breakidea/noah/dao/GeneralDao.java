package com.breakidea.noah.dao;

import java.util.List;

public interface GeneralDao<P, R> {

	public List<R> query(P param);

	public R queryById(Long id);

	public void insert(P param);

	public void update(P param);

	public void delete(P param);
}
