package com.zou.learning.service;

import com.zou.learning.entity.UserDO;

/**
 * @author zou
 * @date 2020-02-11 11:58 下午
 */
public interface IUserService {
    UserDO getUserById(Integer id);

    UserDO addUser(UserDO userDO);

    UserDO modifyUser(UserDO userDO);
}
