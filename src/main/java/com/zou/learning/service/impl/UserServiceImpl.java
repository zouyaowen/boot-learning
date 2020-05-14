package com.zou.learning.service.impl;

import com.zou.learning.dao.IUserDao;
import com.zou.learning.entity.UserDO;
import com.zou.learning.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zou
 * @date 2020-02-11 11:59 下午
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public UserDO getUserById(Integer id) {
        return userDao.selectByPk(id);
    }

    @Override
    public UserDO addUser(UserDO userDO) {
        return userDao.insertUser(userDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDO modifyUser(UserDO userDO) {
        String userName = userDO.getUserName();
        userDO.setPassword("11111111");
        userDO.setUserName(userDO.getUserName() + userDO.getUserName());
        UserDO userDO1 = userDao.updateTransactionalUserDO(userDO);
        if (userDO1 != null) {
            throw new RuntimeException("主业务异常");
        }
        userDO.setPassword(null);
        userDO.setUserName(userName);
        UserDO userDO2 = userDao.updateUser(userDO);
        return userDO;
    }
}
