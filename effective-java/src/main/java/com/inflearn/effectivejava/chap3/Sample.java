package com.inflearn.effectivejava.chap3;

import java.io.Serializable;

public class Sample implements Serializable {
    private Sample() {}

    private Object readResolve() {
        return new Sample();
    }
}
