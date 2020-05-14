package com.zou.learning.dao;

import com.zou.learning.entity.UserDO;

/**
 * @author zou
 * @date 2020-02-11 11:59 下午
 */
public interface IUserDao {

    UserDO selectByPk(Integer id);

    UserDO insertUser(UserDO userDO);

    UserDO updateUser(UserDO userDO);

    UserDO updateTransactionalUserDO(UserDO userDO);
}
