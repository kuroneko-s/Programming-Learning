package com.inflearn.effectivejava.chap9;

import java.io.*;

public class PopularProblem {
    public static void main(String[] args) {
        badSample();
        // goodSample();
    }

    private static void badSample() {
        // 이런식으로 평면구조로 작성할 경우 finally에서 outputStream.close()가 에러가 날 경우,
        // inputStream.close() 는 실행이 되지않음.
        FileOutputStream outputStream = null;
        FileInputStream inputStream = null;
        try {
            outputStream = new FileOutputStream("sample.txt");
            inputStream = inputStream = new FileInputStream(".gitignore");

            // 동작
        } catch (IOException e) {

        } finally {
            // 중첩구조인것처럼 보이지만 finally내에 close가 두번 연달아 있는 평면구조임.
            /**
             * equals
             * finally {
             *  outputStream.close();
             *  inputStream.close();
             * }
             */
            try {
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void goodSample() {
        // 이런식으로 중첩구조로 작성해야함.
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("sample.txt");

            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(".gitignore");
            } catch (IOException e) {

            } finally {
                inputStream.close();
            }
        } catch (IOException e) {

        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
