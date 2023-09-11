package com.inflearn.effectivejava.chap7;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorSample {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        // ExecutorService executorService = Executors.newFixedThreadPool(10); // BlockingQueue 사용
        // ExecutorService executorService = Executors.newCachedThreadPool(); // 자원을 무한정 생성.
        // ExecutorService executorService = Executors.newSingleThreadExecutor();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executorService.submit(new Task());
        }

        System.out.println(Thread.currentThread() + " hello");
        executorService.shutdown();
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread() + " world");
        }
    }
}
