package com.inflearn.effectivejava.chap10.symmetry;

import java.util.ArrayList;
import java.util.List;

public class Sample {
    public static void main(String[] args) {
        CaseInsensitiveString s1 = new CaseInsensitiveString("Hello");
        String s2 = "Hello";

        System.out.println(s1.equals(s2));
        System.out.println(s2.equals(s1));

        List<CaseInsensitiveString> stringList = new ArrayList<>();
        stringList.add(s1);

        System.out.println(stringList.contains(s1));
        System.out.println(stringList.contains(s2));
        // List의 경우 JDK 버전에 따라 혹은 구현방식에 따라 결과가 서로 다르게 나올 수 있음.
    }
}
