package com.alibaba.rigel.shared.service;

import java.util.List;

import com.alibaba.rigel.shared.exception.ServiceException;
import com.alibaba.rigel.shared.param.UserParam;
import com.alibaba.rigel.shared.vo.UserVO;

public interface UserService {

	List<UserVO> query(UserParam param) throws ServiceException;

	void add(UserParam param) throws ServiceException;

	void update(UserParam param) throws ServiceException;

	void delete(UserParam param) throws ServiceException;
}
