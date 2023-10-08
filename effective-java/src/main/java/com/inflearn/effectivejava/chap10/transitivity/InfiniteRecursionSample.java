package com.inflearn.effectivejava.chap10.transitivity;

/**
 * 무한 재귀 호출로 인한 스택 오버플로우 에러 샘플.
 * @see ColorPoint#equals(Object)
 * @see DoubleColorPoint#equals(Object)
 */
public class InfiniteRecursionSample {
    public static void main(String[] args) {
        DoubleColorPoint p1 = new DoubleColorPoint(1, 2, Colors.BLUE, 12);
        ColorPoint p2 = new ColorPoint(1, 2, Colors.BLUE);

        // 무한 재귀. 스택 오버플로우 에러.
        System.out.println(p1.equals(p2));
    }
}
