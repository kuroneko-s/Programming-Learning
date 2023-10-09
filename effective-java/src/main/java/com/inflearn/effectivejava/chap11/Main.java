package com.inflearn.effectivejava.chap11;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // HashMap - none-Thread Safety
        // Hashtable - Thread Safety
        HashMap<PhoneNumber, String> map = new HashMap<>();

        PhoneNumber phone1 = new PhoneNumber(123, 456, 7890);
        PhoneNumber phone2 = new PhoneNumber(123, 456, 7890);

        System.out.println(phone1.equals(phone2));
        System.out.println(phone1.hashCode());
        System.out.println(phone2.hashCode());

        map.put(phone1, "dong");
        map.put(phone2, "hyuk");

        // equals 상에서 같은 객체가 서로 다른 hashcode를 가지고 있다면 HashMap 내부에서 LinkedList로 저장하고 내부에서 equals를 사용해서 찾는다.
        System.out.println(map.get(phone1));
        System.out.println(map.get(phone2));
        System.out.println(map.get(new PhoneNumber(123, 456, 7890))); // hashCode 미구현시 null

    }
}
