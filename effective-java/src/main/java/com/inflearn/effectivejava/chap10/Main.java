package com.inflearn.effectivejava.chap10;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        // broke in java code
        long time = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(time);
        Date date = new Date(time);

        System.out.println(timestamp instanceof Date);
        System.out.println(date.equals(timestamp));
        System.out.println(timestamp.equals(date));

        URL google1 = new URL("https", "about.google", "/products");
        URL google2 = new URL("https", "about.google", "/products");
        System.out.println(google1.equals(google2));

        Objects.equals(null, null);

        List<Point> points = new ArrayList<>();
        points.add(new Point());
        System.out.println(points.contains(new Point()));

        System.out.println(UUID.randomUUID());
    }
}
