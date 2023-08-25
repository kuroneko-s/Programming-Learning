package com.inflearn.effectivejava.chap1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        DefaultHelloService defaultHelloService = new DefaultHelloService(OrderStatus.SHIPPING);

        defaultHelloService.showOrderStatus();

        Arrays.stream(OrderStatus.values()).forEach(orderStatus -> {
            System.out.println(orderStatus.getName());
        });

        EnumMap<OrderStatus, String> enumMap = new EnumMap<>(OrderStatus.class);
        enumMap.put(OrderStatus.SHIPPING, "19237429173");
        enumMap.put(OrderStatus.ORDERED, "1238217893194");

        EnumSet<OrderStatus> enumSet = EnumSet.allOf(OrderStatus.class);

        // ---- Service Loader ----
        // AnnotationConfigApplicationContext - 제공자 등록 API
        // Chap1Config - 서비스 인터페이스 ( Bean )
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Chap1Config.class);
        // getBean - 서비스 접근 API
        HelloService helloService = context.getBean(HelloService.class);

        Class<?> aClass = Class.forName("com.inflearn.effectivejava.chap1.DefaultHelloService");
        Constructor<?> constructor = aClass.getConstructor(OrderStatus.class);
        DefaultHelloService reflectionHelloService = (DefaultHelloService) constructor.newInstance(OrderStatus.ORDERED);
        reflectionHelloService.printHello();
    }
}
