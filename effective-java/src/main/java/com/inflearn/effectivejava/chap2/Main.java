package com.inflearn.effectivejava.chap2;

import org.springframework.boot.jdbc.UnsupportedDataSourcePropertyException;
import org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Pizza nyPizza = new NyPizza.Builder(NyPizza.Size.SMALL)
                .addTopping(Pizza.Topping.PEPPER)
                .build();
        System.out.println(nyPizza.toString());

        Pizza customPizza = new CustomPizza.Builder()
                .addTopping(Pizza.Topping.HAM)
                .customToppings("custom topping")
                .addCustom("dwqjhp")
                .addCustom("q-94u1")
                .build();
        System.out.println(customPizza);


        ArrayList<String> list1 = new ArrayList<>();
        list1.add("123");
        Object obj = list1;
        ArrayList<Integer> list2 = (ArrayList<Integer>)obj;
        list2.add(123);

        List<String> list3 = Collections.checkedList(new ArrayList<>(), String.class);
        list3.add("123");

        Object obj2 = list3;
        List<Integer> list4 = Collections.checkedList((List<Integer>) obj2, Integer.class);
        // list4.add(123); ClassCastException


    }
}
