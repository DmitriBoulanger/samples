package de.dbo.samples.image.houghtransform.api;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Instances are shapes to be painted above components with buffered images.
 * Typically these instances are used in developing/testing to show the
 * ShapeFilter above the marker-image
 * 
 * @see ShapeFilter
 * 
 * @author D.Boulanger Hombach
 * 
 */
public interface Shape {

    public abstract Rectangle getRectangle();

    public abstract double getRectangleRatio();

    public abstract double error();

    public abstract boolean isBorderComplete();

    public abstract boolean isWelldefined();

    public abstract int width();

    public abstract int height();

    public abstract int getTop();

    public abstract int getBottom();

    public abstract int getLeft();

    public abstract int getRight();

    public abstract void setBorder(Rectangle border);

    public abstract void draw(Graphics g, Color ok, Color bad, Point postion);

    public abstract String print();

}
