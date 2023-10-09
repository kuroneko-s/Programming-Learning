package com.inflearn.effectivejava.chap12;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        PhoneNumber phoneNumber = new PhoneNumber(123, 456, 7890);

        System.out.println(phoneNumber.toString());

        short s = 999;
        int i = 999;
        System.out.println(s);
        System.out.println(i);
    }
}
