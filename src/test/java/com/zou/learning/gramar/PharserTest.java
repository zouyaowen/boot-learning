package com.zou.learning.gramar;

import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * @author zou
 * @date 2020-03-01 8:34 下午
 */
public class PharserTest {
    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new MyPhaser();
        PhaserWorker[] phaserWorkers = new PhaserWorker[5];
        for (int i = 0; i < phaserWorkers.length; i++) {
            phaserWorkers[i] = new PhaserWorker(phaser);
            // 注册 Phaser 等待的线程数，执行一次等待线程数 +1
            phaser.register();
        }
        for (int i = 0; i < phaserWorkers.length; i++) {
            // 执行任务
            new Thread(new PhaserWorker(phaser)).start();
        }
    }
    static class PhaserWorker implements Runnable {
        private final Phaser phaser;
        public PhaserWorker(Phaser phaser) {
            this.phaser = phaser;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " | 到达" );
            phaser.arriveAndAwaitAdvance(); // 集合完毕发车
            try {
                Thread.sleep(new Random().nextInt(5) * 1000);
                System.out.println(Thread.currentThread().getName() + " | 到达" );
                phaser.arriveAndAwaitAdvance(); // 景点 1 集合完毕发车
                Thread.sleep(new Random().nextInt(5) * 1000);
                System.out.println(Thread.currentThread().getName() + " | 到达" );
                phaser.arriveAndAwaitAdvance(); // 景点 2 集合完毕发车
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    // Phaser 每个阶段完成之后的事件通知
    static class MyPhaser extends  Phaser{
        @Override
        protected boolean onAdvance(int phase, int registeredParties) { // 每个阶段执行完之后的回调
            switch (phase) {
                case 0:
                    System.out.println("==== 集合完毕发车 ====");
                    return false;
                case 1:
                    System.out.println("==== 景点1集合完毕，发车去下一个景点 ====");
                    return false;
                case 2:
                    System.out.println("==== 景点2集合完毕，发车回家 ====");
                    return false;
                default:
                    return true;
            }
        }
    }
}
