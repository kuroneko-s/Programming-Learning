package com.inflearn.effectivejava.chap7.concurrency;

import lombok.SneakyThrows;

public class ServiceB {
    @SneakyThrows
    public void run() {
        Thread.sleep(300L);
    }
}
