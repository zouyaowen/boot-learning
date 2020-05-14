package com.zou.learning.controller;

import com.zou.learning.entity.UserDO;
import com.zou.learning.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zou
 * @date 2020-02-12 12:01 上午
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/getById")
    UserDO getById(Integer id) {
        UserDO userById = userService.getUserById(id);
        return userById;
    }

    @PostMapping("/add")
    UserDO add(UserDO userDO) {
        return userService.addUser(userDO);
    }

    @PutMapping("/modify")
    UserDO modify(UserDO userDO) {
        return userService.modifyUser(userDO);
    }
}
