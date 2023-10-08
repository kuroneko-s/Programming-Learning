package com.inflearn.effectivejava.chap10.transitivity;

import java.sql.Timestamp;
import java.util.Date;

public class Sample {
    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);

        System.out.println(p1.equals(p2));
        System.out.println(p2.equals(p1));

        ColorPoint p3 = new ColorPoint(1, 2, Colors.RED);
        System.out.println(p1.equals(p3));
        System.out.println(p3.equals(p1)); // false

        ColorPoint x = new ColorPoint(1, 2, Colors.RED);
        Point y = new Point(1, 2);
        ColorPoint z = new ColorPoint(1, 2, Colors.BLUE);
        System.out.println(x.equals(y));
        System.out.println(y.equals(z));
        System.out.println(x.equals(z)); // false. 추이성 깨짐.

        /**
         * 구체 클래스에서 확장해 새로운 값을 추가하면서 equals 규약을 만족시킬 방법은 존재하지 않는다.
         * 자바 공식 클래스에서도 해당 규약은 깨진다.
         * @see java.util.Date
         * @see java.sql.Timestamp
         */
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        Timestamp timestamp = new Timestamp(timeMillis);

        System.out.println(date.equals(timestamp)); // ture
        System.out.println(timestamp.equals(date)); // false

        // instanceof를 getClass로 변경했을 경우. (리스코프 치환 원칙 위배)
        ColorPoint p11 = new ColorPoint(1, 2, Colors.RED);
        Point p12 = new Point(1, 2);
        System.out.println(p11.equals(p12)); // 애초에 같은 클래스가 아니면 에러가 나버림. 상속구조는 집어치우고.

        // 컴포지션 사용
        CompositionPoint cp1 = new CompositionPoint(new Point(1, 2), Colors.RED);
        CompositionPoint cp2 = new CompositionPoint(new Point(1, 2), Colors.RED);
        Point p13 = new Point(1, 2);
        System.out.println(cp1.equals(cp2));
        System.out.println(cp2.equals(cp1));
        System.out.println(cp1.equals(p13)); // false
        System.out.println(cp1.getPoint().equals(p13)); // true
        System.out.println(p13.equals(cp1)); // false
        System.out.println(p13.equals(cp1.getPoint())); // true
    }
}
