package com.inflearn.effectivejava.chap7;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class WeakHashMapCode {
    static Logger log = LoggerFactory.getLogger(WeakHashMapCode.class);

    @SneakyThrows
    public static void main(String[] args) {
        Cache cache = new Cache(new HashMap<>());
        CacheKey cacheKey = new CacheKey(1);
        cache.getValue(cacheKey);

        log.info("gc before cache is Empty ? - {}", cache.getCache().isEmpty());

        cacheKey = null;

        System.out.println("run gc");
        System.gc();
        System.out.println("wait");
        Thread.sleep(3000);

        log.info("gc after cache is Empty ? - {}", cache.getCache().isEmpty());
    }
}
