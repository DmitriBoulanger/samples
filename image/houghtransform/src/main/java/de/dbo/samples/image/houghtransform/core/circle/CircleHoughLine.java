package de.dbo.samples.image.houghtransform.core.circle;

import java.awt.Rectangle;

import de.dbo.samples.image.houghtransform.core.hough.Hough;
import de.dbo.samples.image.houghtransform.core.hough.HoughLineAbstraction;

/**
 * Represents a line as detected by the hough transform. This line is
 * represented by an angle theta and a radius from the center
 * 
 * @author D.Boulanger ITyX GmbH
 * 
 */
public final class CircleHoughLine extends HoughLineAbstraction {

    private CircleHoughLineType type = null;

    /**
     * Initializes the hough line
     */
    public CircleHoughLine(Rectangle rectangle, Integer peak, final Hough hough) {
        super(rectangle, peak, hough);
        initiaze();
    }

    public final Rectangle getRectangle() {
        return rectangle;
    }

    private final void initiaze() {
        if (rectangle.x > 0 && rectangle.y > 0
                && rectangle.width < hough.getWidth()
                && rectangle.height < hough.getHeight()) {
            this.type = CircleHoughLineType.CIRCLE;
        }
        else {
            this.type = CircleHoughLineType.UNKNOWN;
        }
    }

    @Override
    public boolean isShape() {
        switch (type()) {
            case CIRCLE:
                return true;

            default:
                return false;
        }
    }

    @Override
    public boolean isContent() {
        return false;
    }

    @Override
    public boolean isUnknown() {
        return CircleHoughLineType.UNKNOWN == type();
    }

    /**
     * classification of this line
     * 
     * @return type of this line
     * @see CircleHoughLineType
     */
    public CircleHoughLineType type() {
        return this.type;
    }

    @Override
    public String print() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.print());
        sb.append("\ttype=" + type().name().toUpperCase());
        sb.append("\tcenter=[" + (rectangle.x + (rectangle.width / 2)) + ","
                + (rectangle.x + rectangle.height / 2) + "]" + " radius="
                + ((int) ((rectangle.width + rectangle.height) / 4))

                );

        return sb.toString();
    }

}
