package de.dbo.samples.image.houghtransform.core;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.samples.image.houghtransform.api.CategorizerConfiguration;
import de.dbo.samples.image.houghtransform.api.HoughTransformException;
import de.dbo.samples.image.houghtransform.api.CategorizerMode;
import de.dbo.samples.image.houghtransform.api.Category;
import de.dbo.samples.image.houghtransform.api.ImageInfo;
import de.dbo.samples.image.houghtransform.api.ImageQuality;
import de.dbo.samples.image.houghtransform.api.Shape;
import de.dbo.samples.image.houghtransform.api.ShapeFilter;
import de.dbo.samples.image.houghtransform.core.hough.Hough;
import de.dbo.samples.image.houghtransform.core.hough.HoughLines;
import de.dbo.samples.image.houghtransform.core.hough.HoughTransform;
import de.dbo.samples.image.houghtransform.core.hough.Util;

/**
 * Transformation manager. It performs image filtering, Hough Transformations
 * and image classification
 *
 * @author D. Boulanger ITyX GmbH
 *
 */
public class CategorizerWorkerImpl implements HoughTransform {
    private static final Logger              log           = LoggerFactory.getLogger(CategorizerWorkerImpl.class);

    protected final int                      imageWidth;
    protected final int                      imageHeight;

    protected final CategorizerConfiguration cfg;
    protected final ImageQuality          imageQuality;
    protected final String                   imageName;
    protected final BufferedImage            image;
    protected final BufferedImage            imageFiltered;
    protected final Hough                    hough;

    private Category                      imageCategory = null;

    /**
     * production constructor for marker-image categorization
     *
     * @param image
     *            image to be categorized
     *
     * @throws Exception
     */
    public CategorizerWorkerImpl(final BufferedImage imageOrigin, final CategorizerConfiguration cfg) throws HoughTransformException {
        this(imageOrigin, (ImageInfo) null, cfg);
    }

    protected CategorizerWorkerImpl(final BufferedImage image, final ImageInfo info, final CategorizerConfiguration cfg) throws HoughTransformException {
        this.imageWidth = image.getWidth();
        this.imageHeight = image.getHeight();

        if (cfg.getImageErrorMax() < Util.error(this.imageWidth, this.imageHeight)) {
            if (cfg.isEnabled()) {
                throw new HoughTransformException(HoughTransformException.IMANGE_ERROR, "Ratio "
                        + this.imageWidth
                        + "x"
                        + this.imageHeight + " of the Marker-image "
                        + (null == info ? "" : info.name + " ") + "is too large: "
                        + Util.round100(Util.error(this.imageWidth, this.imageHeight)) + "(max=" + cfg.getImageErrorMax() + ")");
            }
            else {
                log.warn("Ratio "
                        + this.imageWidth
                        + "x"
                        + this.imageHeight + " of the Marker-image "
                        + (null == info ? "" : info.name + " ") + "is too large: "
                        + Util.round100(Util.error(this.imageWidth, this.imageHeight)) + "(max=" + cfg.getImageErrorMax() + ")");
            }
        }

        this.imageName = (null == info ? null : info.name);
        this.cfg = cfg;
        this.imageQuality = cfg.imageQuality(this.imageWidth, this.imageHeight);

        // save the image and its clones
        this.image = image;
        this.imageFiltered = applyFilters(this.image, this.cfg);

        // do the transformations
        this.hough = getHoughTransformation();

        log.debug("Mode=" + cfg.type().name() + " Image=" + imageNameOrSize()
                + " Quality=" + imageQuality.name() + " HOUGH: "
                + " max=" + this.hough.getMaxHoughValue().print()
                + " points=" + this.hough.getNumPoints()
                + " radius=" + this.hough.getRadius()
                + " shapePeakMin=" + this.hough.getShapePeakMin()
                + " contentPeakMin=" + this.hough.getContentPeakMin()
                + this.hough.getHoughLines().printHoughLines());
    }

    @Override
    public final boolean isShapeFound() {
        return this.hough.getHoughLines().isShapeFound();
    }

    @Override
    public final boolean isShapeFilterWelldefined() {
        return this.hough.getHoughLines().isShapeWelldefined();
    }

    protected final BufferedImage applyFilters(BufferedImage image, CategorizerConfiguration cfg) throws HoughTransformException {
        BufferedImageOp[] imageFilters;
        try {
            final List<String> filterClassnames = cfg.getImageFilters();
            if (null == filterClassnames || 0 == filterClassnames.size()) {
                return image;
            }
            final int n = filterClassnames.size();
            imageFilters = new BufferedImageOp[n];
            for (int i = 0; i < n; i++) {
                imageFilters[i] = (BufferedImageOp) Class.forName(filterClassnames.get(i)).newInstance();
            }
        }
        catch(Exception e) {
            throw new HoughTransformException(HoughTransformException.SYSTEM, "Cannot initilize image filters", e);
        }
        BufferedImage ret = image;
        for (final BufferedImageOp op : imageFilters) {
            ret = op.filter(ret, null);
        }
        return ret;
    }

    @Override
    public final Category category() {
        if (null != this.imageCategory) {
            return this.imageCategory;
        }
        final Category ret;
        final HoughLines lines = this.hough.getHoughLines();
        switch (this.cfg.type()) {
            case SHAPE:
                if (lines.isShapeFound() && !lines.isContentFound()) {
                    ret = Category.UNCHECKED;
                }
                else if (lines.isContentFound() && lines.isContentFound()) {
                    ret = Category.CHECKED;
                }
                else {
                    ret = Category.UNKNOWN;
                }
                break;
            case CONTENT:
                if (!lines.isContentFound()) {
                    ret = Category.UNCHECKED;
                }
                else if (lines.isContentFound()) {
                    ret = Category.CHECKED;
                }
                else {
                    ret = Category.UNKNOWN;
                }
                break;
            default:
                throw new IllegalStateException(
                        "Should never happen: unexpected type of configuration: "
                                + this.cfg.type().name());
        }
        this.imageCategory = ret;
        return ret;

    }

    @Override
    public Vector<String> description() {
        final Vector<String> ret = new Vector<String>();
        final HoughLines houghLines = this.hough.getHoughLines();
        ret.add("Image: " + imageName().toUpperCase() + "(" + this.imageWidth
                + "x" + this.imageHeight + ")" + " "
                + this.imageQuality.name() + " ERR="
                + Util.round100(Util.error(this.imageWidth, this.imageHeight)));
        ret.add("\nHough Points = " + this.hough.getNumPoints()
                + "  Lines = " + houghLines.getSize() + "  HH = "
                + this.hough.getMaxHoughValue().print());
        ret.add("\nShape: " + houghLines.printShape());
        ret.add("\nShape lines: " + houghLines.printShapeLineCounters());
        ret.add("\nContent lines: " + houghLines.printContentLineCounters());
        ret.add("\nUnkhown lines TOTAL: " + houghLines.getUnknownLines().size());
        return ret;
    }

    private final Hough getHoughTransformation() throws HoughTransformException {
        final HoughLines houghLines = cfg.shapeLines();
        // create a hough transform object with the right dimensions
        final Hough hough = houghInstance(houghLines);

        // add the points from the image (addPoint method can be called separately if points are not in an image)
        hough.addPoints(this.image);
        hough.generateLines();
        return hough;
    }

    private Hough houghInstance(HoughLines lines) throws HoughTransformException {
        final Hough ret;
        final String classname = cfg.getHoughClassname();
        if (null == classname || 0 == classname.trim().length()) {
            throw new HoughTransformException(HoughTransformException.CONFIG_CORRECTNESS,
                    "No class-name for hough found in the transform configuration");
        }
        try {
            final Class<?> lineClass = Class.forName(classname);
            final Class<?>[] filterParameterTypes = {Integer.class, Integer.class, CategorizerConfiguration.class, HoughLines.class};
            final Constructor<?> constructor = lineClass.getConstructor(filterParameterTypes);
            final Object[] lineParameters = {new Integer(this.imageWidth), new Integer(this.imageHeight), this.cfg, lines};
            ret = (Hough) constructor.newInstance(lineParameters);
        }
        catch(Exception e) {
            throw new HoughTransformException(HoughTransformException.SYSTEM,
                    "Cannot create instance for " + classname, e);
        }
        lines.setHough(ret);
        return ret;
    }

    protected final String imageNameOrSize() {
        if (null != this.imageName) {
            return this.imageName;
        }
        else {
            return this.image.getWidth() + "x" + this.image.getHeight();
        }
    }

    @Override
    public BufferedImage getShapeFilteredImage() throws HoughTransformException {
        if (!this.cfg.shapeFilterUsage()) {
            return this.imageFiltered;
        }
        final BufferedImageOp op = this.hough.getHoughLines().getShapeFilter();
        if (null == op) {
            return this.imageFiltered;
        }
        return op.filter(this.imageFiltered, null);
    }

    @Override
    public BufferedImage getShapeCroppedImage() throws HoughTransformException {
        if (!this.cfg.shapeFilterUsage()) {
            return this.imageFiltered;
        }
        ShapeFilter filter = getShapeFilter();
        log.debug("returning shape-filtered image. Filter= " + filter.getClass().getSimpleName());
        return cropImage(getShapeFilteredImage(), filter, cfg);
    }

    protected static final BufferedImage cropImage(BufferedImage image, ShapeFilter filter, final CategorizerConfiguration cfg) {
        final Rectangle filterRectangle = filter.getRectangle();
        return cropImage(image, filterRectangle, cfg);
    }

    protected static final BufferedImage cropImage(final BufferedImage image, final Rectangle shapeRectangle, final CategorizerConfiguration cfg) {
        if (0 != cfg.getWhiteBorder()) {
            return image;
        }

        try {
            final int x = shapeRectangle.x;
            final int y = shapeRectangle.y;
            final int width = (int) shapeRectangle.getWidth();
            final int height = (int) shapeRectangle.getHeight();
            final BufferedImage ret = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB_PRE);
            final Graphics g = ret.getGraphics();
            g.drawImage(image, 0, 0, width, height, x, y, x + width, y + height, null);
            g.dispose();
            return ret;
        }
        catch(Exception e) {
            log.error("Can't crop image", e);
            return image;
        }
    }

    @Override
    public ShapeFilter getShapeFilter() throws HoughTransformException {
        if (!this.cfg.shapeFilterUsage()) {
            return null;
        }
        final ShapeFilter op = this.hough.getHoughLines().getShapeFilter();
        if (null == op) {
            return null;
        }
        else {
            return op;
        }
    }

    @Override
    public Shape getShape() {
        return this.hough.getHoughLines().getShape();
    }

    @Override
    public CategorizerMode getMode() {
        return this.cfg.type();
    }

    @Override
    public int imageWidth() {
        return imageWidth;
    }

    @Override
    public int imageHeight() {
        return imageHeight;
    }

    @Override
    public CategorizerConfiguration configuration() {
        return cfg;
    }

    @Override
    public ImageQuality imageQuality() {
        return imageQuality;
    }

    @Override
    public String imageName() {
        return imageName;
    }

    @Override
    public BufferedImage image() {
        return image;
    }

    @Override
    public BufferedImage imageFiltered() {
        return imageFiltered;
    }

    @Override
    public Hough hough() {
        return hough;
    }

}
