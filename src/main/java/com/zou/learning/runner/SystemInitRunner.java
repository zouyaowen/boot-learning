package com.zou.learning.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author zouyaowen
 * @date 2020-05
 */
@Component
@Slf4j
public class SystemInitRunner implements CommandLineRunner {

    @Override
    @Async
    public void run(String... args) throws Exception {
        runAsync();
        supplyAsync();
    }


    /**
     * 无返回值
     *
     * @throws ExecutionException   执行异常
     * @throws InterruptedException 打断异常
     */
    public void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                log.error("", e);
            }
            System.out.println("run end ...");
        });
        future.get();
    }

    /**
     * 有返回值
     *
     * @throws ExecutionException   执行异常
     * @throws InterruptedException 打断异常
     */
    public static void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                log.error("", e);
            }
            System.out.println("run end ...");
            return System.currentTimeMillis();
        });

        long time = future.get();
        System.out.println("time = " + time);
    }
}
