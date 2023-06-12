package com.wang.test;

import org.junit.Test;
import sun.net.idn.Punycode;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
   public static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            5,
            5,
            10L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(12),
            Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
    @Test
    public void test() throws Exception {


        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(5L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        TimeUnit.SECONDS.sleep(20L);
        System.out.println("executor = " + executor);
    }


    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            executor.shutdownNow();
            while (Thread.activeCount() > 0) {

            }
        }));
    }

}
