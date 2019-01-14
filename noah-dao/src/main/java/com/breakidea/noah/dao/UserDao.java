package com.breakidea.noah.dao;

import org.apache.ibatis.annotations.Mapper;

import com.breakidea.noah.common.model.UserModel;
import com.breakidea.noah.common.param.UserParam;

@Mapper
public interface UserDao extends GeneralDao<UserParam, UserModel> {

}
