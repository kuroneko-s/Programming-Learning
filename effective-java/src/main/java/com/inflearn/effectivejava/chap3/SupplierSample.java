package com.inflearn.effectivejava.chap3;

import java.util.function.Supplier;

public class SupplierSample {
    public SupplierSample(Supplier<MetaSample> sample) {
        MetaSample sampleInstance = sample.get();
        System.out.println("sample instance - " + sampleInstance);
    }
}
