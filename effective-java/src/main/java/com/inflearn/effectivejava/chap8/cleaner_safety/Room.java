package com.inflearn.effectivejava.chap8.cleaner_safety;

import java.lang.ref.Cleaner;

// 안정망으로 사용하는 경우
public class Room implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();
    private final State state;
    private final Cleaner.Cleanable cleanable;

    private static class State implements Runnable {
        int numJunkPiles;

        public State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        @Override
        public void run() {
            System.out.println("방 청소");
            numJunkPiles = 0;
        }
    }

    public Room(int numJunkPiles) {
        this.state = new State(numJunkPiles);
        this.cleanable = cleaner.register(this, state); // 처리가 아니라 등록해주는것. GC 될때 이렇게 처리해달라고 등록해주는거.
    }

    @Override
    public void close() throws Exception {
        cleanable.clean();
    }
}
