package com.inflearn.effectivejava.chap3;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        Log log = LogFactory.getLog(Main.class);

        try {
            Constructor<Sample> declaredConstructor = Sample.class.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            Sample sample = declaredConstructor.newInstance();
            log.info("==========================");
            log.info(sample);
            log.info("==========================");
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        // 제네릭 싱글턴 팩토리
        MetaSample<Integer> sample1 = MetaSample.getInstance();
        MetaSample<String> sample2 = MetaSample.getInstance();

        log.info("---------------------");
        log.info("---------------------");
        log.info(sample1);
        log.info(sample2);

        SupplierSample supplierSample = new SupplierSample(MetaSample::getInstance);

        log.info("-------------------");
        EnumSingleton instance1 = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = EnumSingleton.INSTANCE;
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
    }
}