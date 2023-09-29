package com.inflearn.effectivejava.chap8;

public class RamdaSample {
    private int value = 10;

    private Runnable instanceLambda = () -> {
        System.out.println(value);
    };

    public static void main(String[] args) {

    }
}
