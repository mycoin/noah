package com.breakidea.noah.dao;

import com.breakidea.noah.dao.annotation.AutowireMapper;
import com.breakidea.noah.shared.model.UserModel;
import com.breakidea.noah.shared.param.UserParam;

@AutowireMapper
public interface UserDao extends GeneralDao<UserParam, UserModel> {

}
