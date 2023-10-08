package com.inflearn.effectivejava.chap10.transitivity;

public class CompositionPoint {
    private final Point point;
    private final Colors colors;

    public CompositionPoint(Point point, Colors colors) {
        this.point = point;
        this.colors = colors;
    }

    public Point asPoint() {
        return this.point;
    }

    public Point getPoint() {
        return point;
    }

    public Colors getColors() {
        return colors;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CompositionPoint)) {
            return false;
        }

        CompositionPoint cp = (CompositionPoint) o;
        return cp.getPoint().equals(point) && cp.getColors().equals(colors);
    }
}
