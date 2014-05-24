package de.dbo.samples.image.houghtransform.testimpl;

import java.awt.image.BufferedImage;
import java.util.Vector;

import de.dbo.samples.image.houghtransform.api.CategorizerConfiguration;
import de.dbo.samples.image.houghtransform.api.CategorizerException;
import de.dbo.samples.image.houghtransform.api.Category;
import de.dbo.samples.image.houghtransform.api.ImageInfo;
import de.dbo.samples.image.houghtransform.api.ShapeFilter;
import de.dbo.samples.image.houghtransform.core.CategorizerImpl;
import de.dbo.samples.image.houghtransform.core.CategorizerWorkerImpl;
import de.dbo.samples.image.houghtransform.core.hough.HoughLine;
import de.dbo.samples.image.houghtransform.core.hough.HoughLines;
import de.dbo.samples.image.houghtransform.data.ImageProvider;

final class CategorizerGuiWorker extends CategorizerWorkerImpl {

    final BufferedImage       imageFilteredWithLines;
    private final Category imageCategoryExpected;

    /**
     * developer constructor. It is only used in the GUI-mode
     * @param info
     * @param imageProvider
     * @throws CategorizerException
     * @throws Exception
     */
    CategorizerGuiWorker(final ImageProvider imageProvider, final ImageInfo info
            , CategorizerConfiguration cfg) throws CategorizerException {
        super(CategorizerImpl.preprocess(imageProvider.getImageFromResource(info), cfg), info, cfg);
        this.imageFilteredWithLines = applyFilters(CategorizerImpl.preprocess(imageProvider.getImageFromResource(info), cfg), this.cfg);
        this.imageCategoryExpected = info.category;
        drawHoughTransformLines();
    }


    CategorizerGuiWorker(final BufferedImage image, final ShapeFilter shape, final ImageInfo info, final CategorizerConfiguration cfg)
            throws CategorizerException {
        super(CategorizerImpl.preprocess(cropImage(image, shape, cfg), cfg), info, cfg);
        this.imageFilteredWithLines = applyFilters(CategorizerImpl.preprocess(cropImage(image, shape, cfg), cfg), this.cfg);
        this.imageCategoryExpected = info.category;
        drawHoughTransformLines();
    }

    final Category expectedCategory() {
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
