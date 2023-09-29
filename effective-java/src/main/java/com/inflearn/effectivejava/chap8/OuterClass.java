package com.inflearn.effectivejava.chap8;

import java.lang.reflect.Field;

public class OuterClass {
    class InnerClass {
        public void hello() {
            System.out.println("this - " + this);
            OuterClass.this.printFiled();
        }
    }

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        InnerClass innerClass = outerClass.new InnerClass();

        System.out.println(innerClass);

        // outerClass.printFiled();
        innerClass.hello();
    }

    private void printFiled() {
        Field[] declaredFields = InnerClass.class.getDeclaredFields();
        for (Field filed : declaredFields) {
            System.out.println("field type - " + filed.getType());
            System.out.println("field name - " + filed.getName());
        }
    }
}
