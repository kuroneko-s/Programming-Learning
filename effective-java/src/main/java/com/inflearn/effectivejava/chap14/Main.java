package com.inflearn.effectivejava.chap14;

import com.inflearn.effectivejava.chap13.PhoneNumber;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        BigDecimal bigDecimal1 = new BigDecimal(1111);
        BigDecimal bigDecimal2 = new BigDecimal(2222);
        BigDecimal bigDecimal3 = new BigDecimal(3333);
        BigDecimal bigDecimal4 = new BigDecimal(4444);

        // Comparable
        // Comparator
        Comparator<PhoneNumber> phoneNumberComparator = Comparator.comparingInt(PhoneNumber::getFirstNumber)
                .thenComparingInt(PhoneNumber::getMiddleNumber)
                .thenComparingInt(PhoneNumber::getLastNumber);

        System.out.println(phoneNumberComparator.compare(new PhoneNumber(010, 2213, 5123), new PhoneNumber(521, 4123, 2414)));

        BigDecimal x = new BigDecimal("2.0");
        BigDecimal y = new BigDecimal("2.00");

        System.out.println(x.compareTo(y));
        System.out.println(y.equals(x));


    }
}
