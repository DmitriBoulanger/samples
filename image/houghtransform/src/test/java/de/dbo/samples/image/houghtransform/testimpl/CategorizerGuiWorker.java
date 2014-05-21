package de.dbo.samples.image.houghtransform.testimpl;

import java.awt.image.BufferedImage;
import java.util.Vector;

import de.dbo.samples.image.houghtransform.ImageProvider;
import de.dbo.samples.image.houghtransform.api.OMRCategorizerException;
import de.dbo.samples.image.houghtransform.api.OMRCategory;
import de.dbo.samples.image.houghtransform.api.OMRImageInfo;
import de.dbo.samples.image.houghtransform.api.OMRShapeFilter;
import de.dbo.samples.image.houghtransform.core.Categorizer;
import de.dbo.samples.image.houghtransform.core.CategorizerConfiguration;
import de.dbo.samples.image.houghtransform.core.CategorizerWorker;
import de.dbo.samples.image.houghtransform.core.hough.HoughLine;
import de.dbo.samples.image.houghtransform.core.hough.HoughLines;

final class CategorizerGuiWorker extends CategorizerWorker {

    final BufferedImage       imageFilteredWithLines;
    private final OMRCategory imageCategoryExpected;

    /**
     * developer constructor. It is only used in the GUI-mode
     * @param info
     * @param imageProvider
     * @throws OMRCategorizerException
     * @throws Exception
     */
    CategorizerGuiWorker(final ImageProvider imageProvider, final OMRImageInfo info
            , CategorizerConfiguration cfg) throws OMRCategorizerException {
        super(Categorizer.preprocess(imageProvider.getImageFromResource(info), cfg), info, cfg);
        this.imageFilteredWithLines = applyFilters(Categorizer.preprocess(imageProvider.getImageFromResource(info), cfg), this.cfg);
        this.imageCategoryExpected = info.category;
        drawHoughTransformLines();
    }


    CategorizerGuiWorker(final BufferedImage image, final OMRShapeFilter shape, final OMRImageInfo info, final CategorizerConfiguration cfg)
            throws OMRCategorizerException {
        super(Categorizer.preprocess(cropImage(image, shape, cfg), cfg), info, cfg);
        this.imageFilteredWithLines = applyFilters(Categorizer.preprocess(cropImage(image, shape, cfg), cfg), this.cfg);
        this.imageCategoryExpected = info.category;
        drawHoughTransformLines();
    }

    final OMRCategory expectedCategory() {
        return this.imageCategoryExpected;
    }


    /**
     * pretty-print to be used for logging/debugging
     * @return
     */
    final String print() {
        final StringBuilder ret = new StringBuilder();
        final HoughLines lines = this.hough.getHoughLines();
        ret.append("Image=" + this.imageName().toUpperCase()
                + " (" + this.image.getWidth() + "x" + this.image.getHeight() + ")"
                + " SHAPE=" + lines.isShapeFound()
                + " CONTENT=" + lines.isContentFound());
        return ret.toString();
    }

    private final void drawHoughTransformLines() {
        // get the lines out and draw them
        draw(this.hough.getHoughLines().getUnknownLines(),
                this.imageFilteredWithLines);
        draw(this.hough.getHoughLines().getShapeLines(),
                this.imageFilteredWithLines);
        draw(this.hough.getHoughLines().getContentLines(),
                this.imageFilteredWithLines);
    }

    private static final void draw(final Vector<HoughLine> lines, BufferedImage image) {
        if (null != image) {
            for (int j = 0; j < lines.size(); j++) {
                final HoughLine line = lines.elementAt(j);
                line.draw(image);
            }
        }
        else {
            throw new IllegalStateException(
                    "Method draw(final Vector<HoughLine> lines, BufferedImage image)"
                            + " can NOT draw if image is null!");
        }
    }
}
