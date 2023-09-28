package com.inflearn.effectivejava.chap8;

import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.List;

public class CleanerSampleRun {
    public static void main(String[] args) throws InterruptedException {
        Cleaner cleaner = Cleaner.create();

        List<Object> resourceToCleanUp = new ArrayList<>();
        CleanerSample cleanerSample = new CleanerSample(resourceToCleanUp);
        cleaner.register(cleanerSample, new CleanerSample.ResourceCleaner(resourceToCleanUp));

        cleanerSample = null;
        System.gc();
        Thread.sleep(3000L);
    }
}
