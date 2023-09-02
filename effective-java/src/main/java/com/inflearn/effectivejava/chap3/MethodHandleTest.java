package com.inflearn.effectivejava.chap3;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTest {
    public static void main(String[] args) {
        MethodHandle mh = getMethodHandle();
        System.out.println(mh);

        try {
//            mh.invoke("Hello", null);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static MethodHandle getMethodHandle() {
        MethodHandle mh = null;
        MethodType mt = MethodType.methodType(String.class);
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        try {
            mh = lookup.findVirtual(MethodHandleTest.class, "toString", mt);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return mh;
    }
}
