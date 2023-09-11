package com.inflearn.effectivejava.chap7;

import java.util.Map;

public class Cache {
    Map<CacheKey, String> cache;

    public Cache(Map<CacheKey, String> cache) {
        this.cache = cache;
    }

    public String getValue(CacheKey key) {
        if (this.cache.containsKey(key)) {
            return this.cache.get(key);
        } else {
            int size = cache.size();
            String value = "cache id - " + key.getKey() + "cache count - " + size;
            cache.put(key, value);
            return value;
        }
    }

    public Map<CacheKey, String> getCache() {
        return this.cache;
    }
}
