package com.inflearn.effectivejava.chap1;

public class DefaultHelloService implements HelloService {
    private OrderStatus orderStatus;

    public DefaultHelloService(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public void printHello() {
        System.out.println("default hello service");
    }

    public void showOrderStatus() {
        System.out.println("this order is " + this.orderStatus.getName() + " and status is " + this.orderStatus.isSuccess());
    }
}
