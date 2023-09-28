package com.inflearn.effectivejava.chap8;

public class FinalizerBad {
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
