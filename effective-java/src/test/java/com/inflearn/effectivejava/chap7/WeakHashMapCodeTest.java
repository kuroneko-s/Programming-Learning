package com.inflearn.effectivejava.chap7;

import org.junit.jupiter.api.Test;

import java.util.WeakHashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WeakHashMapCodeTest {
    @Test
    void test() throws InterruptedException {
        Cache cache = new Cache(new WeakHashMap<>());
        CacheKey cacheKey = new CacheKey(1);
        cache.getValue(cacheKey);

        assertFalse(cache.getCache().isEmpty());

        cacheKey = null;

         System.out.println("run gc");
        System.gc();
        System.out.println("wait");
        Thread.sleep(3000);

        assertTrue(cache.getCache().isEmpty());
    }

}