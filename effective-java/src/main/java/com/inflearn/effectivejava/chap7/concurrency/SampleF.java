package com.inflearn.effectivejava.chap7.concurrency;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static com.inflearn.effectivejava.chap7.concurrency.CommonSupport.*;

public class SampleF {
    public static void main(String[] args) {
        start();

        Flux.range(1, USERS)
                .flatMap(number -> Mono.defer(() -> userFlow(number)).subscribeOn(Schedulers.parallel()))
                .blockLast();

        stop();
    }

    private static Mono<String> userFlow(int user) {
        Mono<String> serviceAMono = Mono.defer(() -> Mono.just(serviceA(user))).subscribeOn(Schedulers.boundedElastic());
        Mono<String> serviceBMono = Mono.defer(() -> Mono.just(serviceB(user))).subscribeOn(Schedulers.boundedElastic());

        return serviceAMono.zipWith(serviceBMono, ((s1, s2) -> Flux.range(1, PERSISTENCE_FORK_FACTOR)
                .flatMap(number -> Mono.defer(() -> Mono.just(persistence(number, s1, s2))).subscribeOn(Schedulers.boundedElastic()))
                .blockLast()
        ));
    }
}
