package de.dbo.samples.image.houghtransform.core;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.dbo.samples.image.houghtransform.api.OMRCategorizer;
import de.dbo.samples.image.houghtransform.api.OMRCategorizerException;
import de.dbo.samples.image.houghtransform.api.OMRCategorizerWorker;
import de.dbo.samples.image.houghtransform.api.OMRCategory;
import de.dbo.samples.image.houghtransform.api.OMRImageInfo;
import de.dbo.samples.image.houghtransform.api.OMRShapeFilter;

/**
 * Marker categorization. The module performs classification of the marker
 * images in two steps. The first step is the shape categorization, while the
 * second step is the content categorization
 *
 * @see OMRCategory
 *
 * @author D.Boulanger ITyX GmbH
 */
public class Categorizer implements OMRCategorizer {
    private static final Logger      log                = LoggerFactory.getLogger(Categorizer.class);

    protected OMRCategorizerWorker   shapeCategorizer   = null;
    protected OMRCategorizerWorker   contentCategorizer = null;
    protected BufferedImage          shapeFilteredImage = null;
    protected OMRShapeFilter         shapeFilter        = null;

    private final ApplicationContext ctx;
    private final String             ctxname;

    private final String             bean               = "shapeFinderConfig";
    private final String             bean2              = "contentProcessorConfig";

    private CategorizerConfiguration cfg                = null;
    private CategorizerConfiguration cfg2               = null;

    /**
     * @param ctxname
     *            name of the XML-file (Spring resource)
     * @throws OMRCategorizerException
     */
    public Categorizer(final String ctxname) throws OMRCategorizerException {
        try {
            this.ctxname = ctxname;
            this.ctx = new ClassPathXmlApplicationContext(ctxname);
        }
        catch(Exception exception) {
            throw new OMRCategorizerException(OMRCategorizerException.BEANS_CTX_INITILIZATION,
                    "Initialization of Spring-CTX " + ctxname + " failed", exception);
        }
    }

    /**
     * Special constructor for developing and debugging
     *
     * @param ctxname
     *            name of the XML-file (Spring resource)
     * @param ctx
     *            initialized Spring-context
     */
    public Categorizer(final String ctxname, final ApplicationContext ctx) {
        this.ctxname = ctxname;
        this.ctx = ctx;
    }

    public static final BufferedImage preprocess(final BufferedImage image, final CategorizerConfiguration cfg) {
        return cropImage(image, cfg.getWhiteBorder());
    }

    private static final BufferedImage cropImage(final BufferedImage image, int delta) {
        if (0 == delta) {
            return image;
        }
        final Rectangle border = new Rectangle(delta, delta
                , image.getWidth() - 2 * delta, image.getHeight() - 2 * delta);
        try {
            final int x = border.x;
            final int y = border.y;
            final int width = (int) border.getWidth();
            final int height = (int) border.getHeight();
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
    public final OMRCategory getCategory(final BufferedImage image) throws OMRCategorizerException {
        try {
            cfg = (CategorizerConfiguration) this.ctx.getBean(bean);
        }
        catch(BeansException e) {
            throw new OMRCategorizerException(OMRCategorizerException.BEANS_CONTENT_INITILIZATION,
                    "Failure while creating " + bean + " config-bean from the Spring-CTX " + this.ctxname, e);
        }
        shapeCategorizer = getCategorizerWorker(cropImage(image, cfg.getWhiteBorder()), null, cfg);
        if (0 == cfg.getWhiteBorder()) {
            if (!shapeCategorizer.isShapeFound()) {
                return OMRCategory.UNKNOWN;
            }
            if (!cfg.shapeFilterUsage()) {
                return shapeCategorizer.category();
            }
            if (!shapeCategorizer.isShapeFilterWelldefined()) {
                return shapeCategorizer.category();
            }
        }
        // run the second phase using the shape-filtered image
        // and use its results for the final categorization
        try {
            cfg2 = (CategorizerConfiguration) this.ctx.getBean(bean2);
        }
        catch(BeansException e) {
            throw new OMRCategorizerException(OMRCategorizerException.BEANS_CONTENT_INITILIZATION,
                    "Failure while creating " + bean2 + " config-bean from the Spring-CTX " + this.ctxname, e);
        }
        if (0 == cfg.getWhiteBorder()) {
            shapeFilter = shapeCategorizer.getShapeFilter();
            shapeFilteredImage = shapeCategorizer.getShapeCroppedImage();
            contentCategorizer = getCategorizerWorker(shapeFilteredImage, null, cfg2);
            return contentCategorizer.category();
        }
        else {
            contentCategorizer = getCategorizerWorker(cropImage(image, cfg.getWhiteBorder()), null, cfg2);
            return contentCategorizer.category();
        }
    }

    protected CategorizerWorker getCategorizerWorker(final BufferedImage image, final OMRImageInfo info, final CategorizerConfiguration cfg)
            throws OMRCategorizerException {
        return new CategorizerWorker(image, cfg);
    }

}
