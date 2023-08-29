package com.inflearn.effectivejava.chap2;

import org.springframework.boot.jdbc.UnsupportedDataSourcePropertyException;
import org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata;

import javax.sql.DataSource;

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


    }
}
