package de.dbo.samples.image.houghtransform.core.shapes.signature;

import java.text.DecimalFormat;

import de.dbo.samples.image.houghtransform.core.hough.Hough;
import de.dbo.samples.image.houghtransform.core.hough.HoughLineAbstraction;

/**
 * Represents a line as detected by the hough transform. This line is
 * represented by an angle theta and a radius from the center
 *
 * @author D.Boulanger Hombach
 *
 */
public final class SignatureHoughLine extends HoughLineAbstraction {

    private final int                    x;
    private final int                    y;
    private final SignatureHoughLineType type;

    /**
     * Initializes the hough line
     */
    public SignatureHoughLine(final Double theta, final Double r, final Integer peak, final Hough hough) {
        super(theta, r, peak, hough);
        this.x = postionX();
        this.y = positionY();
        this.type = initialize();
    }

    private final SignatureHoughLineType initialize() {
        if (isHorizontalOrVertical()) {
            return SignatureHoughLineType.UNKNOWN;
        }

        if (isCheck()) {
            return SignatureHoughLineType.CHECK;
        }
        else {
            return SignatureHoughLineType.UNKNOWN;
        }
    }

    private boolean isHorizontalOrVertical() {
        return -1 != this.x || -1 != this.y;
    }

    @Override
    public boolean isShape() {
        return false;
    }

    @Override
    public boolean isContent() {
        switch (type()) {
            case CHECK:
                return true;

            default:
                return false;
        }
    }

    @Override
    public boolean isUnknown() {
        return SignatureHoughLineType.UNKNOWN == type();
    }

    final int position() {
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

    private final boolean isPeakRationHigh() {
        return peakRatio() > 9.0D;
    }

    private final double peakRatio() {
        return (((double) hough.peakMax()) / ((double) peak));
    }
    
    private boolean isCheck() {
        return !isPeakRationHigh();
    }

    /**
     * classification of this line
     *
     * @return type of this line
     * @see BracketsHoughLineType
     */
    public SignatureHoughLineType type() {
        return this.type;
    }

    @Override
    public String print() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.print());
        sb.append("\tmaxPeak=" + hough.peakMax());
        sb.append("\tratioPeak=" + new DecimalFormat("00.00").format(peakRatio()));
        sb.append("\tposition=" + position());
        sb.append("\ttheta=" + new DecimalFormat("00.00").format(theta));
        sb.append("\ttype=" + type().name().toUpperCase());
        return sb.toString();
    }

}
