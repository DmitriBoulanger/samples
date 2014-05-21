package de.dbo.samples.image.houghtransform.core.brackets;

import de.dbo.samples.image.houghtransform.api.HTException;
import de.dbo.samples.image.houghtransform.core.CategorizerConfiguration;
import de.dbo.samples.image.houghtransform.core.hough.HoughLine;
import de.dbo.samples.image.houghtransform.core.hough.HoughLines;
import de.dbo.samples.image.houghtransform.core.hough.HoughLinesAbstraction;
import de.dbo.samples.image.houghtransform.core.hough.Util;

import java.awt.Rectangle;
import java.util.Vector;

/**
 * HoughAlgorithmLinear-Lines extracted form an image
 * 
 * @author D. Boulanger ITyX GmbH
 * 
 */
public final class BracketsHoughLines extends HoughLinesAbstraction implements
        HoughLines {

    private int bracketsRightCnt = 0;
    private int bracketsLeftCnt  = 0;

    private int check01LinesCnt  = 0;
    private int check10LinesCnt  = 0;

    public BracketsHoughLines(final CategorizerConfiguration cfg)
            throws HTException {
        super(cfg);
    }

    public int getBoxLinesLeftCnt() {
        return this.bracketsLeftCnt;
    }

    public int getBoxLinesRightCnt() {
        return this.bracketsRightCnt;
    }

    public int getCheck01LinesCnt() {
        return this.check01LinesCnt;
    }

    public int getCheck10LinesCnt() {
        return this.check10LinesCnt;
    }

    @Override
    public boolean isShapeFound() {
        return this.shapeLines.size() >= this.cfg.getShapeLineCntTotal()
                && this.cfg.getShapeLineCntMin() < this.bracketsLeftCnt
                && this.cfg.getShapeLineCntMin() < this.bracketsRightCnt;
    }

    @Override
    public boolean isShapeWelldefined() {
        return shape.isWelldefined();
    }

    /*
     * total number of lines is high enough, or each line type is high enough
     */
    @Override
    public boolean isContentFound() {
        return this.contentLines.size() >= this.cfg.getContentLineCntTotal()
                || (this.check01LinesCnt >= this.cfg.getContentLineCntMin() && this.check10LinesCnt >= this.cfg
                        .getContentLineCntMin());
    }

    @Override
    public final void complete() {
        if (!isShapeFound()) {
            return;
        }
        final Vector<Integer> lefts = new Vector<Integer>();
        final Vector<Integer> rights = new Vector<Integer>();

        for (final HoughLine line : this.shapeLines) {
            final BracketsHoughLine lineBox = (BracketsHoughLine) line;
            switch (lineBox.type) {
                case BRACKET_LEFT:
                    optAddPosition(lefts, lineBox.x);
                    break;
                case BRACKET_RIGHT:
                    optAddPosition(rights, lineBox.x);
                    break;
                default:
                    break;
            }
        }
        setBorder(lefts, rights);
    }

    private final void setBorder(final Vector<Integer> leftVector,
            final Vector<Integer> rightVector) {

        final int N = 3;
        final int[] lefts = new int[N + 1];
        final int[] rights = new int[N + 1];

        rights[0] = min(rightVector);
        rights[N] = max(rightVector);
        lefts[0] = min(leftVector);
        lefts[N] = max(leftVector);

        int leftstep = (lefts[N] - lefts[0]) / N;
        int rightstep = (rights[N] - rights[0]) / N;

        for (int i = 1; i < N; i++) {
            lefts[i] = lefts[0] + leftstep * i;
            rights[i] = rights[0] + rightstep * i;
        }

        final int top = (int) (getHough().getCenterY() - getHough().getRadius());
        final int bottom = (int) (getHough().getCenterY() + getHough()
                .getRadius());
        // calculate the maximal rectangular and check its error
        final Rectangle rectangleMax = rectangular(lefts[0], top, rights[2],
                bottom);
        if (Util.error(rectangleMax) < shape.getRectangleRatio()) {
            shape.setBorder(rectangleMax);
            return;
        }

        // search for a rectangular with minimal error
        Rectangle rectangleWithMinError = rectangleMax;
        double minError = Double.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int k = 0; k < N; k++) {
                final Rectangle rectangle = rectangular(lefts[i], top,
                        rights[k], bottom);
                final double error = Util.error(rectangle);
                if (error < minError) {
                    rectangleWithMinError = rectangle;
                    minError = error;
                }
            }
        }
        shape.setBorder(rectangleWithMinError);
    }

    private static final void optAddPosition(final Vector<Integer> positions,
            int position) {
        if (0 < position) {
            positions.add(new Integer(position));
        }
    }

    @Override
    public final void add(final HoughLine houghLine) {
        final BracketsHoughLine line = (BracketsHoughLine) houghLine;
        this.lines.add(line);
        switch (line.type) {
            case BRACKET_LEFT:
                this.bracketsLeftCnt++;
                this.shapeLines.add(line);
                break;
            case BRACKET_RIGHT:
                this.bracketsRightCnt++;
                this.shapeLines.add(line);
                break;
            case CHECK_01:
                this.check01LinesCnt++;
                this.contentLines.add(line);
                break;
            case CHECK_10:
                this.check10LinesCnt++;
                this.contentLines.add(line);
                break;

            default:
                this.unknownLines.add(line);
        }
    }

    @Override
    public final String printShapeLineCounters() {
        final StringBuilder ret = new StringBuilder();
        ret.append("Left=" + getBoxLinesLeftCnt() + " Right="
                + getBoxLinesRightCnt() + " TOTAL=" + getShapeLines().size());
        return ret.toString();
    }

    @Override
    public final String printContentLineCounters() {
        final StringBuilder ret = new StringBuilder();
        ret.append(" 01=" + getCheck01LinesCnt() + " 10="
                + getCheck10LinesCnt() + " TOTAL=" + getContentLines().size());
        return ret.toString();
    }
}
