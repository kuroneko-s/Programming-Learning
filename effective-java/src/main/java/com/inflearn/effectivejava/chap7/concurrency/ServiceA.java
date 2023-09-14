package com.inflearn.effectivejava.chap7.concurrency;

import lombok.SneakyThrows;

public class ServiceA {

    @SneakyThrows
    public void run() {
        Thread.sleep(1000L);
    }

}
