package com.breakidea.noah.service.impl;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.breakidea.noah.dao.UserDao;
import com.breakidea.noah.framework.util.EncoderUtils;
import com.breakidea.noah.shared.exception.ServiceException;
import com.breakidea.noah.shared.model.UserModel;
import com.breakidea.noah.shared.param.UserParam;
import com.breakidea.noah.shared.service.UserService;
import com.breakidea.noah.shared.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public void add(UserParam param) throws ServiceException {
		if (!StringUtils.hasLength(param.getPassword())) {
			throw new ServiceException("Bad Parameter", null);
		}

		String password = param.getPassword();
		String encode = null;
		try {
			encode = EncoderUtils.encode(password);
		} catch (GeneralSecurityException e) {
			throw new ServiceException("Bad Password", e);
		}

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
				UserVO vo = new UserVO();
				BeanUtils.copyProperties(model, vo);
				vos.add(vo);
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
}
