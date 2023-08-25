package com.inflearn.effectivejava.chap1;

public enum OrderStatus {
    SHIPPING("배송중", false),
    ORDERED("주문 접수", false),
    FINISH("배송 완료", true),
    RETURN("반품", true);

    OrderStatus(String name, boolean success) {
        this.name = name;
        this.success = success;
    }

    private String name;

    private boolean success;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
