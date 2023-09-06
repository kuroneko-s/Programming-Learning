package com.inflearn.effectivejava.chap5;

public class DefaultDictionary implements Dictionary {
    @Override
    public boolean isValid(String string) {
        return false;
    }
}
