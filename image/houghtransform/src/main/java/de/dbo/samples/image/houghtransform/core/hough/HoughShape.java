package de.dbo.samples.image.houghtransform.core.hough;

import de.dbo.samples.image.houghtransform.api.Shape;

import java.awt.Rectangle;

public abstract class HoughShape implements Shape, Constants {

    protected int          top    = -1;
    protected int          bottom = -1;
    protected int          left   = -1;
    protected int          right  = -1;

    protected final double rectangularRatio;

    protected HoughShape(Double rectangularRatio) {
        this.rectangularRatio = rectangularRatio;
    }

    @Override
    public final boolean isBorderComplete() {
        return this.top > 0 && this.bottom > 0 && this.left > 0
                && this.right > 0;
    }

    @Override
    public final Rectangle getRectangle() {
        return rectangular(left, top, right, bottom);
    }

    @Override
    public final int getTop() {
        return this.top;
    }

    @Override
    public final int getBottom() {
        return this.bottom;
    }

    @Override
    public final int getLeft() {
        return this.left;
    }

    @Override
    public final int getRight() {
        return this.right;
    }

    @Override
    public final int width() {
        return this.right - this.left;
    }

    @Override
    public final int height() {
        return this.bottom - this.top;
    }

    @Override
    public final String print() {
        if (!isBorderComplete()) {
            return "NULL";
        }
        final StringBuilder ret = new StringBuilder();
        if (!isWelldefined()) {
            ret.append(" BAD ");
        }
        ret.append("Top = " + getTop());
        ret.append(" Bottom = " + getBottom());
        ret.append(" Left = " + getLeft());
        ret.append(" Right = " + getRight());
        ret.append(" ERR = " + Util.round10(error()));
        return ret.toString();
    }

    public final void setBorder(final Rectangle rectangle) {
        this.left = rectangle.x;
        this.top = rectangle.y;
        this.right = this.left + rectangle.width;
        this.bottom = this.top + rectangle.height;
    }

    @Override
    public double getRectangleRatio() {
        return rectangularRatio;
    }

    protected static final Rectangle rectangular(int left, int top, int right,
            int bottom) {
        return new Rectangle(left, top, right - left, bottom - top);
    }

    @Override
    public final double error() {
        return Util.error((double) width(), (double) height());
    }
}
