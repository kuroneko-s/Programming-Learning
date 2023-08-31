package com.inflearn.effectivejava.chap3;

public enum EnumSingleton {
    INSTANCE;

    public EnumSingleton getInstance() {
        return INSTANCE;
    }

    public void println() {
        System.out.println("instance - " + INSTANCE);
    }

}
