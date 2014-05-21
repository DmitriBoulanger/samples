package de.dbo.samples.image.houghtransform.core.circle;

import de.dbo.samples.image.houghtransform.api.HTException;
import de.dbo.samples.image.houghtransform.core.CategorizerConfiguration;
import de.dbo.samples.image.houghtransform.core.hough.HoughLine;
import de.dbo.samples.image.houghtransform.core.hough.HoughLines;
import de.dbo.samples.image.houghtransform.core.hough.HoughLinesAbstraction;

/**
 * Lines from HoughAlgorithmLinear.
 * Lines are  extracted form the hough array
 * 
 * @author D. Boulanger ITyX GmbH
 * 
 */
public final class CircleHoughLines extends HoughLinesAbstraction implements
        HoughLines {

    private int circlesCnt = 0;

    public CircleHoughLines(final CategorizerConfiguration cfg)
            throws HTException {
        super(cfg);
    }

    public int getCirclesCnt() {
        return this.circlesCnt;
    }

    public int getCheck01LinesCnt() {
        return 0;
    }

    public int getCheck10LinesCnt() {
        return 0;
    }

    @Override
    public boolean isShapeFound() {
        return 0 < this.circlesCnt;
    }

    @Override
    public boolean isShapeWelldefined() {
        return shape.isWelldefined();
    }

    @Override
    public boolean isContentFound() {
        return false;
    }

    @Override
    public final void complete() {
        if (!isShapeFound()) {
            return;
        }
        setBorder();
    }

    private final void setBorder() {
        // TODO select the best circle! How?
        final CircleHoughLine circle = (CircleHoughLine) shapeLines.get(0);
        shape.setBorder(circle.getRectangle());
    }

    @Override
    public final void add(final HoughLine houghLine) {
        final CircleHoughLine line = (CircleHoughLine) houghLine;
        this.lines.add(line);
        switch (line.type()) {
            case CIRCLE:
                this.circlesCnt++;
                this.shapeLines.add(line);
                break;

            default:
                this.unknownLines.add(line);
        }
    }

    @Override
    public final String printShapeLineCounters() {
        final StringBuilder ret = new StringBuilder();
        ret.append("Circles=" + getCirclesCnt());
        return ret.toString();
    }

    @Override
    public final String printContentLineCounters() {
        final StringBuilder ret = new StringBuilder();
        ret.append("0");
        return ret.toString();
    }
}
