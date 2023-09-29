package com.inflearn.effectivejava.chap8.finalizer_attack;

public class Attacked extends Account {
    public Attacked(String toName) {
        super(toName);
    }


    // 이러한 공격을 막기 위해서 부모 클래스에서 finalize를 final로 빈 메서드로 정의해서 재정의를 못하도록 하거라 클래스 레벨에서 상속불가능하게 만든다.
    @Override
    protected void finalize() throws Throwable {
        this.println("샘플");
    }
}
