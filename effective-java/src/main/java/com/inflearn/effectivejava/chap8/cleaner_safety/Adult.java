package com.inflearn.effectivejava.chap8.cleaner_safety;

public class Adult {
    public static void main(String[] args) {
        try (Room room = new Room(8)) {
            System.out.println("안뇽");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
