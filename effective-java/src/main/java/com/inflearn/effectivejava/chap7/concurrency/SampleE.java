package com.inflearn.effectivejava.chap7.concurrency;

import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import static com.inflearn.effectivejava.chap7.concurrency.CommonSupport.*;

public class SampleE {
    private static final ForkJoinPool commonPool = new ForkJoinPool(2000);

    @SneakyThrows
    public static void main(String[] args) {
        start();

        CompletableFuture.allOf(IntStream.rangeClosed(1, USERS)
                .boxed()
                .map(SampleE::userFlow)
                .toArray(CompletableFuture[]::new)
        ).get();

        stop();
    }

    private static CompletableFuture<String> userFlow(int user) {
        return CompletableFuture.supplyAsync(() -> serviceA(user), commonPool)
                .thenCombine(CompletableFuture.supplyAsync(() -> serviceB(user), commonPool), SampleE::persist);
    }

    private static String persist(String serviceA, String serviceB) {
        CompletableFuture.allOf(IntStream.rangeClosed(1, PERSISTENCE_FORK_FACTOR)
                .boxed()
                .map(integer -> CompletableFuture.runAsync(() -> persistence(integer, serviceA, serviceB), commonPool))
                .toArray(CompletableFuture[]::new)
        ).join();
        return "";
    }
}
