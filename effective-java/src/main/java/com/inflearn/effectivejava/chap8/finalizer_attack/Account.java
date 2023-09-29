package com.inflearn.effectivejava.chap8.finalizer_attack;

public class Account {
    private String toName;

    public Account(String toName) {
        this.toName = toName;

        if (toName.equals("에러")) {
            throw new IllegalArgumentException("에러가 나는 사람이에요.");
        }
    }

    public void println(String from) {
        System.out.println("send to - " + this.toName + " from - " + from);
    }
}
