package com.zou.learning.gramar;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @author zou
 * @date 2020-03-01 8:16 下午
 */
@Slf4j
public class MainTest {
    public static void main(String[] args) {
        //testSemaphore();
        //testExceptionCaught();
        //testThreadPoolExceptionCaught();
        //testMultiThread();
        //testList();
        threadPool();


    }

    private static void threadPool() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 15, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(500), new ThreadPoolExecutor.CallerRunsPolicy());

    }

    private static void testThreadPoolExceptionCaught() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
    }

    private static void testAAA() {
        ArrayList<String> list = new ArrayList<>();
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "10");
        list.parallelStream().forEach(item -> {

        });
    }

    private static void testMultiThread() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            final int a = i;
            executorService.submit(() -> {
                System.out.println("第" + a + "个循环执行");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        for (; ; ) {
            if (executorService.isTerminated()) {
                System.out.println("线程执行完毕");
                LocalDateTime end = LocalDateTime.now();
                System.out.println(end);
                return;
            }
        }
    }

    private static void testExceptionCaught() {
        Thread t = new Thread(() -> {
            //只能抛出unchecked异常
            throw new RuntimeException("运行时异常");
        });
        t.setUncaughtExceptionHandler((Thread thread, Throwable e) -> {
            log.error("捕捉异常成功", e);
        });
        t.start();

    }

    private static void testSemaphore() {
        Semaphore semaphore = new Semaphore(2);
        ThreadPoolExecutor semaphoreThread = new ThreadPoolExecutor(10, 50, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < 5; i++) {
            semaphoreThread.execute(() -> {
                try {
                    // 堵塞获取许可
                    semaphore.acquire();
                    System.out.println("Thread：" + Thread.currentThread().getName() + " 时间：" + LocalDateTime.now());
                    TimeUnit.SECONDS.sleep(2);
                    // 释放许可
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
