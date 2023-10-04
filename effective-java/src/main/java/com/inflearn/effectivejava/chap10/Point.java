package com.inflearn.effectivejava.chap10;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

//@EqualsAndHashCode
@Getter
public class Point {
    private int x;
    private int y;

//    public boolean equals(Point p) {
//        System.out.println("run");
//        return true;
//    }


    @Override
    public boolean equals(Object o) {
        System.out.println("run");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    String id = UUID.randomUUID().toString();

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
