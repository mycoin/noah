package com.breakidea.noah.common.service;

import java.util.List;

import com.breakidea.noah.common.exception.ServiceException;
import com.breakidea.noah.common.parameter.UserParameter;
import com.breakidea.noah.common.vo.UserVO;

public interface UserService {

    List<UserVO> queryList(UserParameter param) throws ServiceException;

    void add(UserParameter param) throws ServiceException;

    void update(UserParameter param) throws ServiceException;

    void delete(UserParameter param) throws ServiceException;
}
