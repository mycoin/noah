package com.breakidea.noah.common.service;

import java.util.List;

import com.breakidea.noah.common.exception.ServiceException;
import com.breakidea.noah.common.param.UserParam;
import com.breakidea.noah.common.vo.UserVO;

public interface UserService {

    List<UserVO> query(UserParam param) throws ServiceException;

    void add(UserParam param) throws ServiceException;

    void update(UserParam param) throws ServiceException;

    void delete(UserParam param) throws ServiceException;
}
