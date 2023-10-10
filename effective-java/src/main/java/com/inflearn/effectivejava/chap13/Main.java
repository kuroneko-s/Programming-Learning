package com.inflearn.effectivejava.chap13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] strings = new String[]{"b", "a", "c"};

        String[] strings1 = Arrays.copyOf(strings, strings.length);

        System.out.println(strings == strings1);
        System.out.println(strings.equals(strings1));

        System.out.println(Arrays.equals(strings, strings1));

        List<String> list1 = new ArrayList<>();
        list1.add("b");
        list1.add("a");
        list1.add("c");
        List<String> list2 = List.copyOf(list1);
        System.out.println(list2);
    }
}
