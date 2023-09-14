package com.inflearn.effectivejava.chap7.concurrency;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static com.inflearn.effectivejava.chap7.concurrency.CommonSupport.*;

public class SampleC {
    private static final int EXECUTOR_THREAD_COUNT = 10;
    private final static ExecutorService executorService = Executors.newScheduledThreadPool(EXECUTOR_THREAD_COUNT);
    private static final CountDownLatch latch = new CountDownLatch(USERS);

    @SneakyThrows
    public static void main(String[] args) {
        start();

        for (int user = 1; user <= 1000; user++) {
            executorService.execute(new UserFlow(user));
        }

        latch.await();
        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);

        stop();
    }

    static class UserFlow implements Runnable {
        private final int user;

        public UserFlow(int user) {
            this.user = user;
        }

        @SneakyThrows
        @Override
        public void run() {
            Future<String> serviceA = executorService.submit(new Service("A", SERVICE_A_LATENCY, user));
            Future<String> serviceB = executorService.submit(new Service("B", SERVICE_B_LATENCY, user));

            for (int i = 1; i <= PERSISTENCE_FORK_FACTOR; i++) {
                executorService.execute(new Persistence(i, serviceA.get(), serviceB.get()));
            }

            latch.countDown();
        }
    }

    static class Persistence implements Runnable {
        private final int fork;
        private final String serviceA;
        private final String serviceB;

        Persistence(int fork, String serviceA, String serviceB) {
            this.fork = fork;
            this.serviceA = serviceA;
            this.serviceB = serviceB;
        }

        @Override
        public void run() {
            persistence(fork, serviceA, serviceB);
        }
    }

    static class Service implements Callable<String> {
        private final String name;
        private final long letency;
        private final int iteration;

        Service(String name, long latency, int iteration) {
            this.name = name;
            this.letency = latency;
            this.iteration = iteration;
        }

        @Override
        public String call() throws Exception {
            return service(name, letency, iteration);
        }
    }

    static class ServiceRunner implements Runnable {
        private List<Thread> savedList = new ArrayList<>();
        @SneakyThrows
        @Override
        public void run() {
            executorService.submit(new ServiceARunner(new ServiceA()));
            executorService.submit(new ServiceBRunner(new ServiceB()));

            for (int i = 0; i < 3; i++) {
                executorService.submit(new Thread(() -> {
                    System.out.println("save...");
                    try {
                        Thread.sleep(300L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }));
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
