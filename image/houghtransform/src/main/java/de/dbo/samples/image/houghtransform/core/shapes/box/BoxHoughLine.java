package de.dbo.samples.image.houghtransform.core.shapes.box;

import static de.dbo.samples.image.houghtransform.core.Constants.ONE;
import static de.dbo.samples.image.houghtransform.core.Constants.PI4;
import static de.dbo.samples.image.houghtransform.core.Constants.PI4x3;

import de.dbo.samples.image.houghtransform.core.hough.Hough;
import de.dbo.samples.image.houghtransform.core.hough.HoughLineAbstraction;

/**
 * Represents a line as detected by the hough transform. This line is
 * represented by an angle theta and a radius from the center
 * 
 * @author D.Boulanger Hombach
 * 
 */
public final class BoxHoughLine extends HoughLineAbstraction {

    private final int              x;
    private final int              y;
    private final BoxHoughLineType type;

    /**
     * Initializes the hough line
     */
    public BoxHoughLine(Double theta, Double r, Integer peak, final Hough hough) {
        super(theta, r, peak, hough);
        this.x = postionX();
        this.y = positionY();
        this.type = initiaze();
    }

    private final BoxHoughLineType initiaze() {
        if (isBoxBottom()) {
            return BoxHoughLineType.BOX_BOTTOM;
        }
        else if (isBoxTop()) {
            return BoxHoughLineType.BOX_TOP;
        }
        else if (isBoxLeft()) {
            return BoxHoughLineType.BOX_LEFT;
        }
        else if (isBoxRight()) {
            return BoxHoughLineType.BOX_RIGHT;
        }
        else if (isCheck01()) {
            return BoxHoughLineType.CHECK_01;
        }
        else if (isCheck10()) {
            return BoxHoughLineType.CHECK_10;
        }
        else {
            return BoxHoughLineType.UNKNOWN;
        }
    }

    private boolean isBoxLeft() {
        return -1 != this.x && this.peak > this.hough.getShapePeakMin()
                && this.x < (ONE - this.hough.cfg.getShapeLineCenterTolerance())
                        * this.hough.getCenterX();
    }

    private boolean isBoxRight() {
        return -1 != this.x && this.peak > this.hough.getShapePeakMin()
                && this.x > (ONE + this.hough.cfg.getShapeLineCenterTolerance())
                        * this.hough.getCenterX();
    }

    private boolean isBoxBottom() {
        return -1 != this.y && this.peak > this.hough.getShapePeakMin()
                && this.y > (ONE + this.hough.cfg.getShapeLineCenterTolerance())
                        * this.hough.getCenterY();
    }

    private boolean isBoxTop() {
        return -1 != this.y 
        		&& this.peak > this.hough.getShapePeakMin()
                && this.y < (ONE - this.hough.cfg.getShapeLineCenterTolerance()) * this.hough.getCenterY();
    }

    @Override
    public boolean isShape() {
        switch (type()) {
            case BOX_BOTTOM:
            case BOX_LEFT:
            case BOX_RIGHT:
            case BOX_TOP:
                return true;

            default:
                return false;
        }
    }

    @Override
    public boolean isContent() {
        switch (type()) {
            case CHECK_01:
            case CHECK_10:
                return true;

            default:
                return false;
        }
    }

    @Override
    public boolean isUnknown() {
        return BoxHoughLineType.UNKNOWN == type();
    }

    int position() {
        if (this.x > 0) {
            return this.x;
        }
        else if (this.y > 0) {
            return this.y;
        }
        else {
            return -1;
        }
    }

    private boolean isCheck01() {
        return equalThetaContent(this.theta, PI4)
                && this.peak > this.hough.getContentPeakMin()
                && (ONE - this.hough.cfg.getContentLineCenterTolerance()) * hough.h < this.r
                && this.r < (ONE + this.hough.cfg.getContentLineCenterTolerance()) * hough.h;
    }

    private boolean isCheck10() {
        return equalThetaContent(this.theta, PI4x3)
                && this.peak > this.hough.getContentPeakMin()
                && (ONE - this.hough.cfg.getContentLineCenterTolerance())
                        * hough.h < this.r
                && this.r < (ONE + this.hough.cfg
                        .getContentLineCenterTolerance()) * hough.h;
    }

    /**
     * classification of this line
     * 
     * @return type of this line
     * @see BracketsHoughLineType
     */
    public BoxHoughLineType type() {
        return this.type;
    }

    @Override
    public String print() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.print());
        sb.append("\tposition=" + position());
        sb.append("\ttype=" + type().name().toUpperCase());

        return sb.toString();
    }

}
