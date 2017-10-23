package com.alibaba.rigel.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.rigel.dao.UserDao;
import com.alibaba.rigel.framework.util.EncoderUtils;
import com.alibaba.rigel.shared.exception.ServiceException;
import com.alibaba.rigel.shared.model.UserModel;
import com.alibaba.rigel.shared.param.UserParam;
import com.alibaba.rigel.shared.service.UserService;
import com.alibaba.rigel.shared.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public void add(UserParam param) throws ServiceException {
		if (!StringUtils.hasLength(param.getPassword())) {
			throw new ServiceException("Bad Parameter", null, null);
		}

		String password = param.getPassword();
		String encode = EncoderUtils.encode(password);

		param.setPassword(encode);
		userDao.insert(param);
	}

	@Override
	public void delete(UserParam param) {
		userDao.delete(param);
	}

	@Override
	public List<UserVO> query(UserParam param) {
		List<UserVO> vos = new ArrayList<UserVO>();
		List<UserModel> models = userDao.query(param);

		if (!CollectionUtils.isEmpty(models)) {
			for (UserModel model : models) {
				vos.add(convert(model));
			}
		}
		return vos;
	}

	@Override
	public void update(UserParam param) {
		UserModel model = userDao.queryById(param.getId());
		if (model == null) {
			userDao.insert(param);
		} else {
			userDao.update(param);
		}
	}

	private UserVO convert(UserModel model) {
		UserVO vo = new UserVO();
		BeanUtils.copyProperties(model, vo);
		return vo;
	}
}
