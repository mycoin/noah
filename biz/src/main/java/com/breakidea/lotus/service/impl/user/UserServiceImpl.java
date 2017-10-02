package com.breakidea.lotus.service.impl.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.breakidea.lotus.common.utils.EncoderUtils;
import com.breakidea.lotus.dao.UserDao;
import com.breakidea.lotus.shared.model.user.UserModel;
import com.breakidea.lotus.shared.param.user.UserParam;
import com.breakidea.lotus.shared.service.user.UserService;
import com.breakidea.lotus.shared.support.ServiceException;
import com.breakidea.lotus.shared.vo.user.UserVO;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void add( UserParam param ) throws ServiceException {
        if (!StringUtils.hasLength(param.getPassword())) {
            throw new ServiceException("Bad Parameter");
        }

        String password = param.getPassword();
        String encode = EncoderUtils.encode(password);

        param.setPassword(encode);
        userDao.insert(param);
    }

    @Override
    public void delete( UserParam param ) {
        userDao.delete(param);
    }

    @Override
    public List<UserVO> query( UserParam param ) {
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
    public void update( UserParam param ) {
        UserModel model = userDao.queryById(param.getId());
        if (model == null) {
            userDao.insert(param);
        } else {
            userDao.update(param);
        }
    }

    private UserVO convert( UserModel model ) {
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }
}
