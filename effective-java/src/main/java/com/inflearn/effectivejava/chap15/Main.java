package com.inflearn.effectivejava.chap15;

import com.inflearn.effectivejava.chap15.anotherScope.Business;
import com.inflearn.effectivejava.chap15.innerScope.Sample;
import com.inflearn.effectivejava.chap15.innerScope.SampleService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Sample sample = new Sample();
    }
}
