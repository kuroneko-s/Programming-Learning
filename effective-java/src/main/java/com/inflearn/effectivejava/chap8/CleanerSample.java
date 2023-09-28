package com.inflearn.effectivejava.chap8;

import java.util.List;

public class CleanerSample {
    private List<Object> resource;

    public CleanerSample(List<Object> resource) {
        this.resource = resource;
    }

    // GC가 동작할때 수행되는 동작.
    public static class ResourceCleaner implements Runnable {

        private List<Object> resourceToClean;

        public ResourceCleaner(List<Object> resourceToClean) {
            this.resourceToClean = resourceToClean;
        }

        @Override
        public void run() {
            resourceToClean = null;
            System.out.println("청소 완료");
        }
    }
}
