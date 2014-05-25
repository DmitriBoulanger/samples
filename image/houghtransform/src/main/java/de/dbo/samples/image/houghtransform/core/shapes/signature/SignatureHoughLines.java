package de.dbo.samples.image.houghtransform.core.shapes.signature;

import de.dbo.samples.image.houghtransform.api.CategorizerConfiguration;
import de.dbo.samples.image.houghtransform.api.CategorizerException;
import de.dbo.samples.image.houghtransform.core.hough.HoughLine;
import de.dbo.samples.image.houghtransform.core.hough.HoughLines;
import de.dbo.samples.image.houghtransform.core.hough.HoughLinesAbstraction;

/**
 * HoughAlgorithmLinear-Lines extracted form an image
 *
 * @author Dmitri Boulanger, Hombach
 *
 */
public final class SignatureHoughLines extends HoughLinesAbstraction implements HoughLines {

    private int checkLinesCnt   = 0;

    public SignatureHoughLines(final CategorizerConfiguration cfg) throws CategorizerException {
        super(cfg);
    }

    public int getBoxLinesTopCnt() {
        return 0;
    }

    public int getBoxLinesBottomCnt() {
        return 0;
    }

    public int getBoxLinesLeftCnt() {
        return 0;
    }

    public int getBoxLinesRightCnt() {
        return 0;
    }

    public int getCheckLinesCnt() {
        return this.checkLinesCnt;
    }

    @Override
    public boolean isShapeFound() {
        return true;
    }

    @Override
    public boolean isShapeWelldefined() {
        return true;
    }

    @Override
    public boolean isContentFound() {
        return this.contentLines.size() >= this.cfg.getContentLineCntTotal()
                && super.getHough().getNumPoints() > 2000;
    }

    @Override
    public final void complete() {

    }

    @Override
    public final void add(final HoughLine houghLine) {
        final SignatureHoughLine line = (SignatureHoughLine) houghLine;
        this.lines.add(line);
        switch (line.type()) {
            case CHECK:
                this.checkLinesCnt++;
                this.contentLines.add(line);
                break;

            default:
                this.unknownLines.add(line);
        }
    }

    @Override
    public final String printShapeLineCounters() {
        final StringBuilder ret = new StringBuilder();
        ret.append("NULL");
        return ret.toString();
    }

    @Override
    public final String printContentLineCounters() {
        final StringBuilder ret = new StringBuilder();
        ret.append(" " + getContentLines().size());
        return ret.toString();
    }
}
