package com.zou.learning.dao.impl;

import com.zou.learning.dao.IUserDao;
import com.zou.learning.entity.UserDO;
import com.zou.learning.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zou
 * @date 2020-02-12 12:28 上午
 */
@Repository
public class UserDao implements IUserDao {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDO selectByPk(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public UserDO insertUser(UserDO userDO) {
        userMapper.insert(userDO);
        return userDO;
    }

    @Override
    public UserDO updateUser(UserDO userDO) {
        userMapper.updateById(userDO);
        return userMapper.selectById(userDO.getId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public UserDO updateTransactionalUserDO(UserDO userDO) {
        userMapper.updateById(userDO);
        return userMapper.selectById(userDO.getId());
    }
}
