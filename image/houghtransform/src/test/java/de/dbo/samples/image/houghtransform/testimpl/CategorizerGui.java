package de.dbo.samples.image.houghtransform.testimpl;

import java.awt.image.BufferedImage;
import java.util.Vector;

import org.springframework.context.ApplicationContext;

import de.dbo.samples.image.houghtransform.ImageProvider;
import de.dbo.samples.image.houghtransform.api.OMRCategorizerException;
import de.dbo.samples.image.houghtransform.api.OMRCategory;
import de.dbo.samples.image.houghtransform.api.OMRImageInfo;
import de.dbo.samples.image.houghtransform.api.OMRShape;
import de.dbo.samples.image.houghtransform.core.Categorizer;
import de.dbo.samples.image.houghtransform.core.CategorizerConfiguration;
import de.dbo.samples.image.houghtransform.core.CategorizerWorker;
import de.dbo.samples.image.houghtransform.core.hough.HoughLines;
import de.dbo.samples.image.houghtransform.core.hough.HoughTransform;

final class CategorizerGui extends Categorizer {

    private final ImageProvider imageProvider;
    private final OMRImageInfo  info;

    CategorizerGui(final ImageProvider imageProvider, final OMRImageInfo info,
            final String ctxname, final ApplicationContext ctx) throws OMRCategorizerException {
        super(ctxname, ctx);
        this.imageProvider = imageProvider;
        this.info = info;

    }

    @Override
    protected CategorizerWorker getCategorizerWorker(final BufferedImage _0, final OMRImageInfo _1
            , final CategorizerConfiguration cfg) throws OMRCategorizerException {
        if (null == shapeFilteredImage) {
            return new CategorizerGuiWorker(this.imageProvider, this.info, cfg);
        }
        else {
            return new CategorizerGuiWorker(imageProvider.getImageFromResource(this.info)
                    , shapeFilter, this.info, cfg);
        }
    }

    final OMRCategory category() throws OMRCategorizerException {
        return getCategory(imageProvider.getImageFromResource(info));
    }

    final OMRImageInfo info() {
        return this.info;
    }

    final BufferedImage image() {
        return ((HoughTransform) this.shapeCategorizer).image();
    }

    final BufferedImage imageFiltered() {
        return ((HoughTransform) this.shapeCategorizer).imageFiltered();
    }

    final OMRShape getShape() {
        return this.shapeCategorizer.getShape();
    }

    final BufferedImage getBoxFilterdImage() {
        if (null != this.contentCategorizer) {
            return ((HoughTransform) this.contentCategorizer).image();
        }
        else {
            return ((HoughTransform) this.shapeCategorizer).image();
        }
    }

    final BufferedImage imageFilteredWithLines() {
        if (null != this.contentCategorizer) {
            return ((CategorizerGuiWorker) this.contentCategorizer).imageFilteredWithLines;
        }
        else {
            return ((CategorizerGuiWorker) this.shapeCategorizer).imageFilteredWithLines;
        }
    }

    final BufferedImage getHoughArrayImage() {
        return ((HoughTransform) this.shapeCategorizer).hough().getHoughArrayImage();

    }

    final BufferedImage getHoughArrayImage2() {
        if (null != this.contentCategorizer) {
            return ((HoughTransform) this.contentCategorizer).hough().getHoughArrayImage();
        }
        else {
            return ((HoughTransform) this.shapeCategorizer).hough().getHoughArrayImage();
        }
    }

    final Vector<String> shapeDescription() {
        return this.shapeCategorizer.description();
    }

    final Vector<String> contentDescription() {
        if (null != this.contentCategorizer) {
            return this.contentCategorizer.description();
        }
        else {
            return this.shapeCategorizer.description();
        }
    }

    final HoughLines getHoughLines() {
        if (null != this.contentCategorizer) {
            return ((HoughTransform) this.contentCategorizer).hough().getHoughLines();
        }
        else {
            return ((HoughTransform) this.shapeCategorizer).hough().getHoughLines();
        }
    }

    final OMRCategory expectedCategory() {
        if (null != this.contentCategorizer) {
            return ((CategorizerGuiWorker) this.contentCategorizer).expectedCategory();
        }
        else {
            return ((CategorizerGuiWorker) this.shapeCategorizer).expectedCategory();
        }
    }
}
