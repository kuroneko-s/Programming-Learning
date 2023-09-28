package com.inflearn.effectivejava.chap8;

import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;

public class FinalizerSample {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        int i = 0;

        while(true) {
            i++;
            new FinalizerBad();

            if ((i % 1_000_000) == 0) {
                Class<?> finalizerClass = Class.forName("java.lang.ref.Finalizer");
                Field queue = finalizerClass.getDeclaredField("queue");
                queue.setAccessible(true);
                ReferenceQueue<Object> referenceQueue = (ReferenceQueue<Object>) queue.get(null);

                Field queueLength = ReferenceQueue.class.getDeclaredField("queueLength");
                queueLength.setAccessible(true);
                long l = (long) queueLength.get(referenceQueue);

                System.out.println("references is " + l);
            }
        }
    }
}
