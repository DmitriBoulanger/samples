package de.dbo.samples.image.houghtransform.core.hough;

import de.dbo.samples.image.houghtransform.api.CategorizerException;
import de.dbo.samples.image.houghtransform.api.Shape;
import de.dbo.samples.image.houghtransform.api.ShapeFilter;

import java.util.Vector;

public interface HoughLines {

    public abstract Hough getHough();

    public abstract void setHough(Hough hough);

    /**
     * vector containing all accumulated hough-lines
     * 
     * @return vector with hough-lines
     */
    public abstract Vector<HoughLine> getLines();

    /**
     * vector containing only shape hough-lines
     * 
     * @return vector with hough-lines
     */
    public abstract Vector<HoughLine> getShapeLines();

    /**
     * vector containing only content hough-lines
     * 
     * @return vector with hough-lines
     */
    public abstract Vector<HoughLine> getContentLines();

    /**
     * vector containing only unknown (unclassified) hough-lines
     * 
     * @return vector with hough-lines
     */
    public abstract Vector<HoughLine> getUnknownLines();

    /**
     * total counter of all accumulated hough-lines
     * 
     * @return counter
     */
    public abstract int getSize();

    /**
     * counter of accumulated shape hough-lines
     * 
     * @return counter
     */
    public abstract int getShapeLinesCnt();

    /**
     * counter of accumulated content hough-lines
     * 
     * @return counter
     */
    public abstract int getContentLinesCnt();

    /**
     * counter of accumulated unknown (unclassified) hough-lines
     * 
     * @return counter
     */
    public abstract int getUnknownLinesCnt();
    
    public abstract int getShapePixelCnt();
    public abstract int getContentPixelCnt();
    public abstract int getUnknownPixelCnt();
    
    /**
     * @return true only if the shape has been recognized
     */
    public abstract boolean isShapeFound();

    /**
     * @return true only if the shape has been recognized
     */
    public abstract boolean isShapeWelldefined();

    /**
     * @return true only if the shape-based filter is well-defined
     */
    public abstract boolean isShapeFilter();

    /**
     * image filter to erase all pixels outside the shape and possibly the shape
     * itself
     * 
     * @return shape
     */
    public ShapeFilter getShapeFilter() throws CategorizerException;

    /**
     * shape to draw
     * 
     * @return shape
     */
    public Shape getShape();

    /**
     * @return true only if the contents has been recognized
     */
    public abstract boolean isContentFound();

    /**
     * add new hough-line and classify it
     */
    public abstract void add(final HoughLine line);

    /**
     * when all lines have been added, perform final classification of the shape
     */
    public abstract void complete();

    /**
     * description of the accumulated hough-lines
     * 
     * @return vector containing text-lines
     */
    public abstract Vector<String> description();

    /**
     * pretty-print to show accumulated hough-lines.
     * 
     * @return string suitable to print by a logger/debugger
     */
    public abstract String printHoughLines();

    /**
     * pretty-print to show parameters of the shape.
     * 
     * @return string suitable to print by a logger/debugger
     */
    public abstract String printShape();

    /**
     * pretty-print to show counters of the shape.
     * 
     * @return string suitable to print by a logger/debugger
     */
    public abstract String printShapeLineCounters();

    /**
     * pretty-print to show counters of the content.
     * 
     * @return string suitable to print by a logger/debugger
     */
    public abstract String printContentLineCounters();
    
    public abstract String printUnknownLineCounters();
    
    public abstract String printLineCounters();

}