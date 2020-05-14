package com.zou.learning.service.impl;

import com.alibaba.fastjson.JSON;
import com.zou.learning.entity.UserDO;
import com.zou.learning.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author zou
 * @date 2020-02-14 2:27 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    private IUserService userService;

    @Test
    public void getUserById() {
        UserDO userById = userService.getUserById(1);
        System.out.println(JSON.toJSONString(userById,true));
    }

    @Test
    public void addUser() {
    }

    @Test
    public void modifyUser() {
    }
}