package com.inflearn.effectivejava.chap8;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.Cleaner;

public class CloseableSample implements AutoCloseable {
    private InputStream inputStream;

    public CloseableSample(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    static class CloseableCleaner implements Runnable {
        private InputStream inputStream;

        public CloseableCleaner(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void close() throws Exception {
        this.inputStream.close();
    }
}
