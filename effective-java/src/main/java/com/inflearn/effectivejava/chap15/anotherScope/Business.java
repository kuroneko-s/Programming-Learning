package com.inflearn.effectivejava.chap15.anotherScope;

import com.inflearn.effectivejava.chap15.innerScope.SampleService;

import java.util.Objects;

public class Business {
    SampleService sampleService;

    public Business(SampleService sampleService) {
        this.sampleService = Objects.requireNonNull(sampleService);
    }

    public void businessHello() {
        this.sampleService.hell();
    }
}
