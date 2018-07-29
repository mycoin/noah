package com.breakidea.noah.shared.service;

import java.util.List;

import com.breakidea.noah.shared.exception.ServiceException;
import com.breakidea.noah.shared.param.UserParam;
import com.breakidea.noah.shared.vo.UserVO;

public interface UserService {

	List<UserVO> query(UserParam param) throws ServiceException;

	void add(UserParam param) throws ServiceException;

	void update(UserParam param) throws ServiceException;

	void delete(UserParam param) throws ServiceException;
}
