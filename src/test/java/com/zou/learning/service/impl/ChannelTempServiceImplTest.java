package com.zou.learning.service.impl;

import com.zou.learning.service.IChannelTempService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author zouyaowen
 * @date 2020-05
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ChannelTempServiceImplTest {

    @Autowired
    private IChannelTempService channelTempService;

    @Test
    public void sendMsg() {
        channelTempService.sendMsg();
    }
}