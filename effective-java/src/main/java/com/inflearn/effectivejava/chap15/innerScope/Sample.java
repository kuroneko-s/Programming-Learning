package com.inflearn.effectivejava.chap15.innerScope;

import java.util.Arrays;

public class Sample implements SampleService {
    @Override
    public void hell() {
        System.out.println("Hello");
    }

    private class PrivateClass {
        public void privateClassHello() {
            System.out.println(this);
            hell();
        }
    }

    private static class PrivateStaticClass {
        public void privateStaticClassHello() {
            System.out.println(this);
            // hell(); // 접근 안됨.
        }
    }

    public static void main(String[] args) {
        Arrays.stream(PrivateClass.class.getDeclaredFields()).forEach(System.out::println);
    }
}
