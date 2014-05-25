package de.dbo.samples.image.houghtransform.core.hough;

import java.awt.Rectangle;
import java.lang.reflect.Constructor;
import java.util.Vector;

import de.dbo.samples.image.houghtransform.api.CategorizerConfiguration;
import de.dbo.samples.image.houghtransform.api.CategorizerException;
import de.dbo.samples.image.houghtransform.api.Shape;
import de.dbo.samples.image.houghtransform.api.ShapeFilter;

/**
 * JUnit of hough-Lines extracted form an image
 *
 * @author Dmitri Boulanger, Hombach
 *
 */
public abstract class HoughLinesAbstraction implements HoughLines {

    protected final CategorizerConfiguration cfg;
    protected final Shape                 shape;

    protected final Vector<HoughLine>        lines        = new Vector<HoughLine>();
    protected final Vector<HoughLine>        shapeLines   = new Vector<HoughLine>();
    protected final Vector<HoughLine>        contentLines = new Vector<HoughLine>();
    protected final Vector<HoughLine>        unknownLines = new Vector<HoughLine>();

    private Hough                            hough        = null;

    protected HoughLinesAbstraction(final CategorizerConfiguration cfg) throws CategorizerException {
        this.shape = cfg.shape();
        this.cfg = cfg;
    }

    @Override
    public final void setHough(Hough hough) {
        this.hough = hough;
    }

    @Override
    public final Hough getHough() {
        return hough;
    }

    @Override
    public final boolean isShapeFilter() {
        return cfg.shapeFilterUsage() && shape.isWelldefined();
    }

    @Override
    public final ShapeFilter getShapeFilter() throws CategorizerException {
        if (!isShapeFilter()) {
            return null;
        }
        final String classname = cfg.getShapeFilterClassname();
        if (null == classname || 0 == classname.trim().length()) {
            throw new CategorizerException(
                    CategorizerException.CONFIG_CORRECTNESS,
                    "No class-name for shape-filter found in the transform configuration");
        }
        try {
            final Class<?> filterClass = Class.forName(classname);
            final Class<?>[] filterParameterTypes = {Shape.class,
                    Integer.class};
            final Constructor<?> constructor = filterClass
                    .getConstructor(filterParameterTypes);
            final Object[] fileterParameters = {shape,
                    new Integer(cfg.getShapeFilterMargin())};
            return (ShapeFilter) constructor.newInstance(fileterParameters);
        }
        catch(Exception e) {
            throw new CategorizerException(CategorizerException.SYSTEM,
                    "Cannot create instance for " + classname, e);
        }
    }

    @Override
    public final Shape getShape() {
        if (!shape.isBorderComplete()) {
            return null;
        }
        return shape;
    }

    @Override
    public final Vector<HoughLine> getLines() {
        return this.lines;
    }

    @Override
    public final Vector<HoughLine> getShapeLines() {
        return this.shapeLines;
    }

    @Override
    public final Vector<HoughLine> getContentLines() {
        return this.contentLines;
    }

    @Override
    public final Vector<HoughLine> getUnknownLines() {
        return this.unknownLines;
    }

    @Override
    public final int getSize() {
        return this.lines.size();
    }

    @Override
    public final int getShapeLinesCnt() {
        return this.shapeLines.size();
    }

    @Override
    public final int getContentLinesCnt() {
        return this.contentLines.size();
    }

    @Override
    public final int getUnknownLinesCnt() {
        return this.unknownLines.size();
    }

    @Override
    public abstract boolean isShapeFound();

    @Override
    public abstract boolean isContentFound();

    @Override
    public abstract void add(final HoughLine line);

    @Override
    public Vector<String> description() {
        final Vector<String> ret = new Vector<String>();
        ret.add("\nSHAPE = " + isShapeFound() + " CONTENT = " + isContentFound());
        ret.add("\nShape lines: " + getShapeLinesCnt());
        ret.add("\nContent-lines:  " + getContentLinesCnt());
        ret.add("\nUnknown lines  = " + getUnknownLinesCnt());
        return ret;
    }

    @Override
    public final String printHoughLines() {
        final StringBuilder ret = new StringBuilder();
        for (final HoughLine houghLine : this.lines) {
                        if (houghLine.isContent()) {
                ret.append("\n\t - " + houghLine.print());
                        }

        }
        return ret.toString();
    }

    @Override
    public final String printShape() {
        return shape.print();
    }

    protected static final Rectangle rectangular(int left, int top, int right, int bottom) {
        return new Rectangle(left, top, right - left, bottom - top);
    }

    protected static final int min(final Vector<Integer> positions) {
        if (positions.isEmpty()) {
            throw new IllegalStateException(
                    "min(final Vector<Integer> positions): No elements!");
        }
        int min = Integer.MAX_VALUE;
        int x = 0;
        for (final Integer postion : positions) {
            x = postion.intValue();
            if (x < min) {
                min = x;
            }
        }
        return min;
    }

    protected static final int max(final Vector<Integer> positions) {
        if (positions.isEmpty()) {
            throw new IllegalStateException(
                    "max(final Vector<Integer> positions): No elements!");

        }
        int max = Integer.MIN_VALUE;
        int x = 0;
        for (final Integer postion : positions) {
            x = postion.intValue();
            if (x > max) {
                max = x;
            }
        }
        return max;
    }

}
