package com.inflearn.effectivejava.chap7.concurrency;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class SampleB {
    static int count = 0;
    @SneakyThrows
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            count++;
            System.out.println(count);

            Thread thread = new Thread(new ServiceRunner());
            thread.start();
            threadList.add(thread);
        }

        for (Thread thread : threadList) {
            thread.join();
        }

        long resultTime = System.currentTimeMillis() - startTime;
        System.out.println("걸린 시간 - " + resultTime);
    }

    static class ServiceRunner implements Runnable {
        private List<Thread> savedList = new ArrayList<>();
        @SneakyThrows
        @Override
        public void run() {
            Thread serviceAThread = new Thread(new ServiceARunner(new ServiceA()));
            Thread serviceBThread = new Thread(new ServiceBRunner(new ServiceB()));
            serviceAThread.run();
            serviceBThread.run();

            serviceAThread.join();
            serviceBThread.join();

            for (int i = 0; i < 3; i++) {
                Thread thread = new Thread(() -> {
                    System.out.println("save...");
                    try {
                        Thread.sleep(300L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

                this.savedList.add(thread);
            }

            for (Thread thread : savedList) {
                thread.join();
            }
        }
    }

    static class ServiceBRunner implements Runnable {
        private ServiceB serviceB;

        public ServiceBRunner(ServiceB serviceB) {
            this.serviceB = serviceB;
        }

        @Override
        public void run() {
            serviceB.run();
        }
    }

    static class ServiceARunner implements Runnable {
        private ServiceA serviceA;

        public ServiceARunner(ServiceA serviceA) {
            this.serviceA = serviceA;
        }

        @Override
        public void run() {
            serviceA.run();
        }
    }
}
