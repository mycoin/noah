package com.breakidea.noah.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.breakidea.noah.common.exception.ServiceException;
import com.breakidea.noah.common.model.UserModel;
import com.breakidea.noah.common.parameter.UserParameter;
import com.breakidea.noah.common.service.UserService;
import com.breakidea.noah.common.vo.UserVO;
import com.breakidea.noah.dao.UserDao;
import com.breakidea.noah.support.Encoder;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void add(UserParameter param) throws ServiceException {
        if (!StringUtils.hasLength(param.getPassword())) {
            throw new ServiceException("Bad Parameter", null);
        }

        String password = param.getPassword();
        String encode = Encoder.encodeQuietly(password);

        param.setPassword(encode);
        param.setStatus("SK");

        userDao.insert(param);
    }

    @Override
    public void delete(UserParameter param) {
        userDao.delete(param);
    }

    @Override
    public List<UserVO> queryList(UserParameter param) {
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
    public void update(UserParameter param) {
        UserModel model = userDao.queryById(param.getId());
        if (model == null) {
            userDao.insert(param);
        } else {
            userDao.update(param);
        }
    }
}
