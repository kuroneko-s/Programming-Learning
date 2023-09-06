package com.inflearn.effectivejava.chap5;

public class Main {
    public static void main(String[] args) {
        Sample sample = new Sample(DefaultDictionary::new);
        sample.isValid("tet");

        Sample sample1 = new Sample(() -> new DefaultDictionaryFactory().get());
    }
}
