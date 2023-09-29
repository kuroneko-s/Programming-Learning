package com.inflearn.effectivejava.chap8.cleaner_safety;

public class Teenager {
    public static void main(String[] args) {
        new Room(99);
        System.out.println("안뇽!!");
    }
}
