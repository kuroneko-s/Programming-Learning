package com.inflearn.effectivejava.chap3;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

        MethodHandles.Lookup lookup = MethodHandles.lookup();

        List<Person> personList = new ArrayList<>();
        personList.add(new Person());
        personList.add(new Person());

        Comparator<Person> compare = (Comparator<Person>) Person::compare;
        personList.sort(compare);

        // Externalizable
    }

    static class Person implements Serializable {
        private static final long serialVersionUID = -2212162887670277371L;
        private int number;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int compare(Person p1) {
            return p1.number - this.number;
        }
    }
}