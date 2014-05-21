package de.dbo.samples.image.houghtransform.core.signature;

import de.dbo.samples.image.houghtransform.api.OMRCategorizerException;
import de.dbo.samples.image.houghtransform.core.CategorizerConfiguration;
import de.dbo.samples.image.houghtransform.core.hough.HoughLine;
import de.dbo.samples.image.houghtransform.core.hough.HoughLines;
import de.dbo.samples.image.houghtransform.core.hough.HoughLinesAbstraction;

/**
 * HoughAlgorithmLinear-Lines extracted form an image
 *
 * @author D. Boulanger ITyX GmbH
 *
 */
public final class SignatureHoughLines extends HoughLinesAbstraction implements HoughLines {


    private int check01LinesCnt   = 0;
    private int check10LinesCnt   = 0;

    public SignatureHoughLines(final CategorizerConfiguration cfg) throws OMRCategorizerException {
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

    public int getCheck01LinesCnt() {
        return this.check01LinesCnt;
    }

    public int getCheck10LinesCnt() {
        return this.check10LinesCnt;
    }

    @Override
    public boolean isShapeFound() {
        return true;
    }

    @Override
    public boolean isShapeWelldefined() {
        return true;
    }

    /*
     * total number of lines is high enough,
     * or each line type is high enough
     */
    @Override
    public boolean isContentFound() {
        return this.contentLines.size() >= this.cfg.getContentLineCntTotal()
                && (this.check01LinesCnt >= this.cfg.getContentLineCntMin()
                && this.check10LinesCnt >= this.cfg.getContentLineCntMin())
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
        ret.append("NULL");
        return ret.toString();
    }

    @Override
    public final String printContentLineCounters() {
        final StringBuilder ret = new StringBuilder();
        ret.append(" 01=" + getCheck01LinesCnt() + " 10=" + getCheck10LinesCnt() + " TOTAL=" + getContentLines().size());
        return ret.toString();
    }
}
