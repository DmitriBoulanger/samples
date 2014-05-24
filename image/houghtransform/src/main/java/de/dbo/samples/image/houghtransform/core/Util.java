package de.dbo.samples.image.houghtransform.core;

import java.awt.Rectangle;

public final class Util implements Constants {

    public static double round1(double value) {
        return round(value, ONE);
    }

    public static double round10(double value) {
        return round(value, TEN);
    }

    public static double round1000(double value) {
        return round(value, THOUSAND);
    }

    public static double round100(double value) {
        return round(value, HUNDRED);
    }

    public static double round(double value, double scale) {
        return Math.round(value * scale) / scale;
    }

    public static final boolean equal(double x1, double x2, double eps) {
        return Math.abs(x1 - x2) <= eps;
    }

    public static final double diagonal(int width, int height) {
        final double widthDouble = (double) width;
        final double heightDouble = (double) height;
        return diagonal(widthDouble, heightDouble);
    }

    public static final double diagonal(double width, double height) {
        return Math.sqrt(width * width + height * height);
    }

    public static final double error(final Rectangle r) {
        return error((double) r.getWidth(), (double) r.getHeight());
    }

    public static final double error(double width, double height) {
        final double whRatio = ((double) width) / ((double) height);
        final double hwRatio = ((double) height) / ((double) width);
        final double ratio = whRatio < 1.00D ? whRatio : hwRatio;
        return ONE - ratio;
    }

    public static int ratio(double value, int procent) {
        return (int) (value * (((double) procent) / HUNDRED));
    }

    public static int ratio(int value, int procent) {
        return ratio((double) value, procent);
    }

    public static final int distance(int x, int y, double centerX, double centerY) {
        return (int) Math.sqrt(distance2(x, y, centerX, centerY));
    }

    public static final double distance2(int x, int y, double centerX, double centerY) {
        final double distnaceX = ((double) x) - centerX;
        final double distanceY = ((double) y) - centerY;
        return distnaceX * distnaceX + distanceY * distanceY;
    }
}
