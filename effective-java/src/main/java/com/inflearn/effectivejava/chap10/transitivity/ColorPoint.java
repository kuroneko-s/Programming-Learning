package com.inflearn.effectivejava.chap10.transitivity;

public class ColorPoint extends Point {
    private final Colors color;

    public ColorPoint(int x, int y, Colors color) {
        super(x, y);
        this.color = color;
    }

    public Colors getColor() {
        return color;
    }

    // 대칭성 위배.
//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof ColorPoint)) {
//            return false;
//        }
//
//        return super.equals(o) && ((ColorPoint) o).color == color;
//    }

    /**
     * 추이성 위반. 대칭성은 지켜지지만 추이성이 깨짐 && if (!(o instanceof ColorPoint)) 이부분에서 무한 재귀 현상에 빠질 수 있음.
     * @see DoubleColorPoint
     */
//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof Point)) return false;
//        if (!(o instanceof ColorPoint)) return o.equals(this); // color에 대한 비교는 하지않겠다.
//        return super.equals(o) && ((ColorPoint) o).color == color;
//    }

    // 리스코프 치환 원칙 위배 (SOLID에서 L)
    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Point p = (Point) o;
        return p.getX() == x && p.getY() == y;
    }
}
