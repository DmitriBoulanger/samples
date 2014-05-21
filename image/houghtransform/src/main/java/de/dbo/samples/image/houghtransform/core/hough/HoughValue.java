package de.dbo.samples.image.houghtransform.core.hough;

public final class HoughValue {

    public final int value;
    public final int x;
    public final int y;

    public HoughValue(int value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
    }

    public final String print() {
        return value + "[" + x + "," + y + "]";
    }
}
