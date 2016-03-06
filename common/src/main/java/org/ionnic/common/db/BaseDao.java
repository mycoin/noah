package org.ionnic.common.db;

import java.util.List;

public interface BaseDao {

    public <T> List<T> query(String sql, Object[] parameters, Class<T> cl);
}
