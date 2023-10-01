package com.inflearn.effectivejava.chap9;

import java.io.IOException;

public class BadExample {
    public static void main(String[] args) {
        // 좋지 않음.
        // read에 대한 Exception은 로깅이 되지 않음.
        SampleReader reader = null;
        try {
            reader = new SampleReader(".gitignore");
            reader.read();
        } catch (IOException e) {
        } finally {
            reader.close();
        }
    }
}
