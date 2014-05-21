package de.dbo.samples.image.houghtransform.core.box;

import de.dbo.samples.image.houghtransform.api.OMRCategorizerException;
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
public final class BoxHoughLines extends HoughLinesAbstraction implements
        HoughLines {

    private int boxLinesTopCnt    = 0;
    private int boxLinesBottomCnt = 0;
    private int boxLinesLeftCnt   = 0;
    private int boxLinesRightCnt  = 0;

    private int check01LinesCnt   = 0;
    private int check10LinesCnt   = 0;

    public BoxHoughLines(final CategorizerConfiguration cfg)
            throws OMRCategorizerException {
        super(cfg);
    }

    public int getBoxLinesTopCnt() {
        return this.boxLinesTopCnt;
    }

    public int getBoxLinesBottomCnt() {
        return this.boxLinesBottomCnt;
    }

    public int getBoxLinesLeftCnt() {
        return this.boxLinesLeftCnt;
    }

    public int getBoxLinesRightCnt() {
        return this.boxLinesRightCnt;
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
                && 0 < this.boxLinesTopCnt && 0 < this.boxLinesBottomCnt
                && 0 < this.boxLinesLeftCnt && 0 < this.boxLinesRightCnt;
    }

    @Override
    public boolean isShapeWelldefined() {
        return shape.isWelldefined();
    }

    /*
     * total number of lines is high enough, 
     * or each line type is high enough
     */
    @Override
    public boolean isContentFound() {
        return this.contentLines.size() >= this.cfg.getContentLineCntTotal()
                || (this.check01LinesCnt >= this.cfg.getContentLineCntMin()
                && this.check10LinesCnt >= this.cfg.getContentLineCntMin());
    }

    @Override
    public final void complete() {
        if (!isShapeFound()) {
            return;
        }
        final Vector<Integer> top = new Vector<Integer>();
        final Vector<Integer> bottom = new Vector<Integer>();
        final Vector<Integer> left = new Vector<Integer>();
        final Vector<Integer> right = new Vector<Integer>();

        for (final HoughLine line : this.shapeLines) {
            final BoxHoughLine lineBox = (BoxHoughLine) line;
            switch (lineBox.type()) {
                case BOX_TOP:
                    optAddPosition(top, lineBox.position());
                    break;
                case BOX_BOTTOM:
                    optAddPosition(bottom, lineBox.position());
                    break;
                case BOX_LEFT:
                    optAddPosition(left, lineBox.position());
                    break;
                case BOX_RIGHT:
                    optAddPosition(right, lineBox.position());
                    break;
                default:
                    break;
            }
        }
        setBorder(top, bottom, left, right);
    }

    private final void setBorder(final Vector<Integer> topVector,
            final Vector<Integer> bottomVector,
            final Vector<Integer> leftVector, final Vector<Integer> rightVector) {

        final int N = 3;
        final int[] tops = new int[N + 1];
        final int[] bottoms = new int[N + 1];
        final int[] lefts = new int[N + 1];
        final int[] rights = new int[N + 1];

        tops[0] = min(topVector);
        tops[N] = max(topVector);
        bottoms[0] = min(bottomVector);
        bottoms[N] = max(bottomVector);
        rights[0] = min(rightVector);
        rights[N] = max(rightVector);
        lefts[0] = min(leftVector);
        lefts[N] = max(leftVector);

        int topstep = (tops[N] - tops[0]) / N;
        int bottomstep = (bottoms[N] - bottoms[0]) / N;
        int leftstep = (lefts[N] - lefts[0]) / N;
        int rightstep = (rights[N] - rights[0]) / N;

        for (int i = 1; i < N; i++) {
            tops[i] = tops[0] + topstep * i;
            bottoms[i] = bottoms[0] + bottomstep * i;
            lefts[i] = lefts[0] + leftstep * i;
            rights[i] = rights[0] + rightstep * i;
        }

        // calculate the maximal rectangular and check its error
        final Rectangle rectangleMax = rectangular(lefts[0], tops[0],
                rights[2], bottoms[2]);
        if (Util.error(rectangleMax) < shape.getRectangleRatio()) {
            shape.setBorder(rectangleMax);
            return;
        }

        // search for a rectangular with minimal error
        Rectangle rectangleWithMinError = rectangleMax;
        double minError = Double.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    for (int l = 0; l < N; l++) {
                        final Rectangle rectangle = rectangular(lefts[i],
                                tops[j], rights[k], bottoms[l]);
                        final double error = Util.error(rectangle);
                        if (error < minError) {
                            rectangleWithMinError = rectangle;
                            minError = error;
                        }
                    }
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
        final BoxHoughLine line = (BoxHoughLine) houghLine;
        this.lines.add(line);
        switch (line.type()) {
            case BOX_TOP:
                this.boxLinesTopCnt++;
                this.shapeLines.add(line);
                break;
            case BOX_BOTTOM:
                this.boxLinesBottomCnt++;
                this.shapeLines.add(line);
                break;
            case BOX_LEFT:
                this.boxLinesLeftCnt++;
                this.shapeLines.add(line);
                break;
            case BOX_RIGHT:
                this.boxLinesRightCnt++;
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
        ret.append("Top=" + getBoxLinesTopCnt() + " Bottom="
                + getBoxLinesBottomCnt() + " Left=" + getBoxLinesLeftCnt()
                + " Right=" + getBoxLinesRightCnt() + " TOTAL="
                + getShapeLines().size());
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
