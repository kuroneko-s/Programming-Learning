package com.inflearn.effectivejava.chap10.transitivity;

public class DoubleColorPoint extends Point{
    private final Colors color;
    private final int size;

    public DoubleColorPoint(int x, int y, Colors color, int size) {
        super(x, y);
        this.color = color;
        this.size = size;
    }

    public Colors getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    /**
     * 추이성 위반. 대칭성은 지켜지지만 추이성이 깨짐 && if (!(o instanceof ColorPoint)) 이부분에서 무한 재귀 현상에 빠질 수 있음.
     * @see ColorPoint
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) return false;
        if (!(o instanceof DoubleColorPoint)) return o.equals(this); // color에 대한 비교는 하지않겠다.
        return super.equals(o) && ((DoubleColorPoint) o).color == color;
    }
}
