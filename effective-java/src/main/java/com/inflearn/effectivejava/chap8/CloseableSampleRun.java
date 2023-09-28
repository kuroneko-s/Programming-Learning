package com.inflearn.effectivejava.chap8;

import java.io.FileInputStream;

public class CloseableSampleRun {
    public static void main(String[] args) {
        try (CloseableSample sample = new CloseableSample(new FileInputStream("D:/test"))) {

        } catch(Exception e) {

        }
    }
}
