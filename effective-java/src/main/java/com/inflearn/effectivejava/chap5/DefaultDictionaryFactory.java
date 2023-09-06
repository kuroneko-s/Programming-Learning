package com.inflearn.effectivejava.chap5;

public class DefaultDictionaryFactory implements DictionaryFactory {
    @Override
    public Dictionary get() {
        return new DefaultDictionary();
    }
}
