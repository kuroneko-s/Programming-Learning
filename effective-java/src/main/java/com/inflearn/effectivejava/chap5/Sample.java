package com.inflearn.effectivejava.chap5;

import java.util.function.Supplier;

public class Sample {
    private final Dictionary dictionary;

    public Sample(Supplier<? extends Dictionary> supplier) {
        this.dictionary = supplier.get();
    }

    public boolean isValid(String word) {
        return this.dictionary.isValid(word);
    }
}
