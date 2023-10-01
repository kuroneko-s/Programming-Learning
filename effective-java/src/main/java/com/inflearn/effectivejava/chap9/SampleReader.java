package com.inflearn.effectivejava.chap9;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SampleReader extends FileReader {
    public SampleReader(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    @Override
    public int read() throws IllegalCallerException {
        throw new IllegalCallerException();
    }

    @Override
    public void close() throws IllegalStateException {
        throw new IllegalStateException();
    }
}
