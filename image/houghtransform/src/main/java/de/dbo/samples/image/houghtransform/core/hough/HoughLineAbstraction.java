package de.dbo.samples.image.houghtransform.core.hough;

import static de.dbo.samples.image.houghtransform.core.Constants.CONTENT_COLOR_RGB;
import static de.dbo.samples.image.houghtransform.core.Constants.WHITE_COLOR_RGB;
import static de.dbo.samples.image.houghtransform.core.Constants.PI;
import static de.dbo.samples.image.houghtransform.core.Constants.PI2;
import static de.dbo.samples.image.houghtransform.core.Constants.PI4;
import static de.dbo.samples.image.houghtransform.core.Constants.PI4x3;
import static de.dbo.samples.image.houghtransform.core.Constants.RADIUS_FORMAT;
import static de.dbo.samples.image.houghtransform.core.Constants.SHAPE_COLOR_RGB;
import static de.dbo.samples.image.houghtransform.core.Constants.THETA_FORMAT;
import static de.dbo.samples.image.houghtransform.core.Constants.PIXEL_FORMAT;
import static de.dbo.samples.image.houghtransform.core.Constants.UNKNOWN_COLOR_RGB;
import static de.dbo.samples.image.houghtransform.core.Constants.ZERO;

import de.dbo.samples.image.houghtransform.core.Util;

import java.awt.Rectangle;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a line as detected by the hough transform. This line is
 * represented by an angle theta and a radius from the center
 *
 * @author D.Boulanger Hombach
 *
 */
public abstract class HoughLineAbstraction implements HoughLine {

    protected final Rectangle        rectangle;
    protected final double           theta;
    protected final double           r;
    protected final int              peak;
    protected final HoughAbstraction hough;

    /**
     * Initializes basics of a hough line for linear algorithm
     */
    protected HoughLineAbstraction(double theta, double r, int peak, final Hough hough) {
        this.hough = (HoughAbstraction) hough;
        this.theta = theta;
        this.r = r;
        this.peak = peak;
        this.rectangle = null;
    }

    /**
     * Initializes basics of a hough line for circle algorithm
     */
    protected HoughLineAbstraction(final Rectangle rectangle, int peak, final Hough hough) {
        this.hough = (HoughAbstraction) hough;
        this.theta = -1;
        this.r = -1;
        this.peak = peak;
        this.rectangle = rectangle;
    }

    protected final int postionX() {
        if (isPi0(this.theta)) {
            return (int) (((this.r - (hough.houghHeight))) + hough.getCenterX());
        }
        else if (isPiPi(this.theta)) {
            return (int) (((-this.r + (hough.houghHeight))) + hough.getCenterX());
        }
        else {
            return -1;
        }
    }

    protected final int positionY() {
        if (isPi2(this.theta)) {
            return (int) (((this.r - hough.houghHeight)) + hough.getCenterY());
        }
        else {
            return -1;
        }
    }

    protected final boolean isPiPi(double theta) {
        return equalThetaShape(theta, PI);
    }

    private final boolean isPi0(double theta) {
        return equalThetaShape(theta, ZERO);
    }

    private final boolean isPi2(double theta) {
        return equalThetaShape(theta, PI2);
    }

    protected final boolean equalThetaShape(double x1, double x2) {
        return Util.equal(x1, x2, hough.cfg.getShapeLineThetaEps());
    }

    protected final boolean equalThetaContent(double x1, double x2) {
        return Util.equal(x1, x2, hough.cfg.getContentLineThetaEps());
    }

    @Override
    public final void draw(final BufferedImage image) {
        final int color = this.color();
        for (final Point pixel:pixels()) {
        	image.setRGB(pixel.x, pixel.y, color);
        }
       
    }
    
    @Override
    public final void draw(final BufferedImage image, final List<Point> pixels) {
    	if (null==pixels) {
    		return;
    	}
        
        final int color = this.color();
        for (final Point pixel:pixels) {
        	image.setRGB(pixel.x, pixel.y, color);
        }
    }
    
    private static final boolean isCoordinate(final int x, final int y
    		, final int width , final int height, final BufferedImage image) {
    	return x < width && x >= 0 
    			&& y < height && y >= 0
    			&& image.getRGB(x, y)!=WHITE_COLOR_RGB;
    }
    
    private static final void add(final boolean[][] points, final Point point 
    	, final int width , final int height,final BufferedImage image) {
    	if ( isCoordinate(point.x,point.y, width, height,image)) {
    		points[point.x][point.y] = true;
    	}
    }
    
	private static final void addNeighbourhood( final boolean[][] points
			,final int x, final int y, final int width, final int height
			,final BufferedImage image) {

		add(points, new Point(x + 1, y + 1), width, height,image);
		add(points, new Point(x    , y + 1), width, height,image);
		add(points, new Point(x + 1, y    ), width, height,image);
		add(points, new Point(x - 1, y - 1), width, height,image);
		add(points, new Point(x    , y - 1), width, height,image);
		add(points, new Point(x - 1, y    ), width, height,image);

	}
	
	private static final void addNeighbourhoodHorizontal(final boolean[][] points,
			final int x, final int y, final int width, final int height
			,final BufferedImage image) {
		add(points, new Point(x + 1, y), width, height,image);
		add(points, new Point(x - 1, y), width, height,image);
	}
	
	private static final void addNeighbourhoodVertical(final boolean[][] points,
			final int x, final int y, final int width, final int height
			,final BufferedImage image) {
		add(points, new Point(x, y + 1), width, height,image);
		add(points, new Point(x, y - 1), width, height,image);
	}
    
    protected List<Point> pixels = null;
    @Override
    public final List<Point> pixels() {
    	return pixels;
    }
    
    protected void collectPixels(final BufferedImage image) {
    	if (null!=pixels) {
    		return;
    	}
        final int height = image.getHeight();
        final int width = image.getWidth();
        final float centerX = width / 2;
        final float centerY = height / 2;

        // Collect pixels in the output array
        final boolean[][] points = new boolean[width][height];
       
        final double tsin = Math.sin(this.theta);
        final double tcos = Math.cos(this.theta);

        if (this.theta < PI4 || this.theta > PI4x3) { // Vertical lines 
            for (int y = 0; y < height; y++) {
                int x = (int) ((((this.r - this.hough.houghHeight) - ((y - centerY) * tsin)) / tcos) + centerX);
                if (x < width && x >= 0) {
                	if (image.getRGB(x, y)==WHITE_COLOR_RGB) {
                		continue;
                	}
                	points[x][y] = true;
                	addNeighbourhood(points,x,y,width,height,image);
                }
            }
        }
        else { // Horizontal lines
            for (int x = 0; x < width; x++) {
                int y = (int) ((((this.r - this.hough.houghHeight) - ((x - centerX) * tcos)) / tsin) + centerY);
                if (y < height && y >= 0) {
                	if (image.getRGB(x, y)==WHITE_COLOR_RGB) {
                		continue;
                	}
                	points[x][y] = true;
                	addNeighbourhood(points,x,y,width,height,image);
                }
            }
        }
        
        pixels = new ArrayList<Point>();
        for (int x = 0; x < width; x++) { 
        	 for (int y = 0; y < height; y++) { 
        		 if (points[x][y]) {
        			 pixels.add(new Point(x,y));
        		 }
        }
        }
    }

    @Override
    public final int color() {
        if (isShape()) {
            return SHAPE_COLOR_RGB;
        }
        else if (isContent()) {
            return CONTENT_COLOR_RGB;
        }
        else {
            return UNKNOWN_COLOR_RGB;
        }
    }

    @Override
    public String print() {
        final StringBuilder sb = new StringBuilder();
        sb.append(" pixel=" + PIXEL_FORMAT.format(pixels().size()));
        sb.append(" theta=" + THETA_FORMAT.format(Util.round1000(this.theta)));
        sb.append(" r=" + RADIUS_FORMAT.format(Util.round1(this.r)));
        sb.append(" peak=" + this.peak);
        return sb.toString();
    }

}
