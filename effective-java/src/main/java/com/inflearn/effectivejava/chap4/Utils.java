package com.inflearn.effectivejava.chap4;

public abstract class Utils {
    private Utils() {
        throw new AssertionError();
    }

    public static String hello() {
        return "Hello";
    }
}
