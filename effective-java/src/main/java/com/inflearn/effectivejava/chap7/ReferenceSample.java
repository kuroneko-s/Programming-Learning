package com.inflearn.effectivejava.chap7;

import java.lang.ref.WeakReference;

public class ReferenceSample {
    public static void main(String[] args) throws InterruptedException {
        Object strong = new Object();
//        SoftReference<Object> softReference = new SoftReference<>(strong);
        WeakReference<Object> weakReference = new WeakReference<>(strong);

//        System.out.println(softReference.get());
        System.out.println(weakReference.get());
        strong = null;

        System.gc();
        Thread.sleep(5000);

//        System.out.println(softReference.get());
        System.out.println(weakReference.get());
    }
}
