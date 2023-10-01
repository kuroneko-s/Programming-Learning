package com.inflearn.effectivejava.chap9;

import java.io.IOException;

public class GoodExample {
    public static void main(String[] args) {
        // 좋음
        try (SampleReader reader = new SampleReader(".gitignore")) {
            reader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
