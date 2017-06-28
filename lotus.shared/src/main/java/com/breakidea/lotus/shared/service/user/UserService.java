package com.breakidea.lotus.shared.service.user;

import java.util.List;

import com.breakidea.lotus.shared.param.user.UserParam;
import com.breakidea.lotus.shared.support.ServiceException;
import com.breakidea.lotus.shared.vo.user.UserVO;

public interface UserService {

	List<UserVO> query(UserParam param) throws ServiceException;

	void add(UserParam param) throws ServiceException;

	void update(UserParam param) throws ServiceException;

	void delete(UserParam param) throws ServiceException;
}
