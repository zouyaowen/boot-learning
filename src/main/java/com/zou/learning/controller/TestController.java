package com.zou.learning.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zou
 * @date 2020-02-02 11:18 下午
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("/getData")
    public void getData(String aa){
        log.info("----------Get Data---------"+aa);
    }

    @PostMapping("/postData")
    public void postData(String bb){
        log.info("----------Post Data---------"+bb);
    }
}
