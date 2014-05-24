package de.dbo.samples.image.houghtransform.core.shapes.brackets;

import static de.dbo.samples.image.houghtransform.core.Constants.ONE;
import static de.dbo.samples.image.houghtransform.core.Constants.PI4;
import static de.dbo.samples.image.houghtransform.core.Constants.PI4x3;

import de.dbo.samples.image.houghtransform.core.hough.Hough;
import de.dbo.samples.image.houghtransform.core.hough.HoughLineAbstraction;

/**
 * Represents a line as detected by the hough transform. This line is
 * represented by an angle theta and a radius
 * 
 * @author D.Boulanger ITyX GmbH
 * 
 */
public final class BracketsHoughLine extends HoughLineAbstraction {

    final int                   x;
    final BracketsHoughLineType type;

    /**
     * Initializes the hough line
     */
    public BracketsHoughLine(Double theta, Double r, Integer peak,
            final Hough hough) {
        super(theta, r, peak, hough);
        this.x = postionX();
        this.type = initiaze();
    }

    private final BracketsHoughLineType initiaze() {
        if (isBracketLeft()) {
            return BracketsHoughLineType.BRACKET_LEFT;
        }
        else if (isBracketRight()) {
            return BracketsHoughLineType.BRACKET_RIGHT;
        }
        else if (isCheck01()) {
            return BracketsHoughLineType.CHECK_01;
        }
        else if (isCheck10()) {
            return BracketsHoughLineType.CHECK_10;
        }
        else {
            return BracketsHoughLineType.UNKNOWN;
        }
    }

    private final boolean isBracketLeft() {
        return -1 != this.x && this.peak > this.hough.getShapePeakMin()
                && this.x < (ONE - this.hough.cfg.getShapeLineCenterTolerance())
                        * this.hough.getCenterX();
    }

    private final boolean isBracketRight() {
        return -1 != this.x && this.peak > this.hough.getShapePeakMin()
                && this.peak > this.hough.getContentPeakMin()
                && this.x > (ONE + this.hough.cfg.getShapeLineCenterTolerance())
                        * this.hough.getCenterX();
    }

    @Override
    public boolean isShape() {
        switch (type) {
            case BRACKET_LEFT:
            case BRACKET_RIGHT:
                return true;

            default:
                return false;
        }
    }

    @Override
    public boolean isContent() {
        switch (type) {
            case CHECK_01:
            case CHECK_10:
                return true;

            default:
                return false;
        }
    }

    @Override
    public boolean isUnknown() {
        return BracketsHoughLineType.UNKNOWN == type;
    }

    private boolean isCheck01() {
        return equalThetaContent(this.theta, PI4)
                && this.peak > this.hough.getContentPeakMin()
                && (ONE - this.hough.cfg.getContentLineCenterTolerance())
                        * hough.h < this.r
                && this.r < (ONE + this.hough.cfg
                        .getContentLineCenterTolerance()) * hough.h;
    }

    private boolean isCheck10() {
        return equalThetaContent(this.theta, PI4x3)
                && this.peak > this.hough.getContentPeakMin()
                && (ONE - this.hough.cfg.getContentLineCenterTolerance())
                        * hough.h < this.r
                && this.r < (ONE + this.hough.cfg
                        .getContentLineCenterTolerance()) * hough.h;
    }

    @Override
    public String print() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.print());
        sb.append("\tx=" + this.x);
        sb.append("\ttype=" + type.name().toUpperCase());
        return sb.toString();
    }
}
