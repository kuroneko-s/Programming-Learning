package com.inflearn.effectivejava.chap3;

public class MetaSample<T> {
    private static final MetaSample<Object> INSTANCE = new MetaSample<>();

    private MetaSample() {}

    @SuppressWarnings("unchecked")
    static public <T> MetaSample<T> getInstance() {
        return (MetaSample<T>) INSTANCE;
    }
}
