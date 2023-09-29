package com.inflearn.effectivejava.chap8.finalizer_attack;

public class FinalizerAttack {
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account("동혁");
        account.println("샘플");

//        Account account2 = new Account("에러");
//        account2.println("되나 ? ");
        Account account2 = null;
        try {
             account2 = new Attacked("에러");
        } catch (Exception e) {
            System.out.println("에러났지만");
        }
        System.gc();
        Thread.sleep(3000L);
    }
}
