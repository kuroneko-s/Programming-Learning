package com.inflearn.effectivejava.chap6;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");

        System.out.println(str1.hashCode() == str2.hashCode());
        System.out.println(str1 == str2);
        System.out.println(str1.equals(str3));
        System.out.println(str1.hashCode() == str3.hashCode());
        System.out.println(str1 == str3);

        // Sample sample = new Sample();

        Pattern pattern = Pattern.compile("test");
        String test = pattern.matcher("test31r1r321howqfhitesthiowthoi31").replaceAll("12345");
        System.out.println(test);
    }
}


