package com.inflearn.effectivejava.chap7;

import java.util.Optional;
import java.util.OptionalInt;

public class Main {
    public static void main(String[] args) {
        Optional<Object> optional = Optional.of("Hello");

        optional.ifPresent(System.out::println);

        OptionalInt optionalInt = OptionalInt.of(123);


    }
}
