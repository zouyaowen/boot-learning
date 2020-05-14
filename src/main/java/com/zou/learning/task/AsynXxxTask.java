package com.zou.learning.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @author zou
 * @date 2020-03-01 10:49 下午
 */
@Component
public class AsynXxxTask {

    /**
     * 闭锁对象
     */
    private CountDownLatch countDownLatch;

    @Async
    public void task(Integer param, CountDownLatch countDownLatch) {
        System.out.println("-------------");
        countDownLatch.countDown();
    }
}
