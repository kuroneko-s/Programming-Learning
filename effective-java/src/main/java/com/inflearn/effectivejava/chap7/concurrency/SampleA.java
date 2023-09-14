package com.inflearn.effectivejava.chap7.concurrency;

import lombok.SneakyThrows;

/**
 * https://homoefficio.github.io/2020/12/11/Java-Concurrency-Evolution/
 * 샘플 코드.
 */

public class SampleA {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            ServiceA serviceA = new ServiceA();
            ServiceB serviceB = new ServiceB();
            serviceA.run();
            serviceB.run();
            for (int j = 0; j < 3; j++) {
                persistent(serviceA, serviceB);
            }
        }


        long endTime = System.currentTimeMillis() - startTime;

        System.out.println("걸린 시간 - " + endTime);
    }

    @SneakyThrows
    public static void persistent(ServiceA serviceA, ServiceB serviceB) {
        System.out.println("save...");
        Thread.sleep(300L);
    }
}

