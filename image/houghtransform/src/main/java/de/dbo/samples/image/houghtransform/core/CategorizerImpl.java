package de.dbo.samples.image.houghtransform.core;

import static de.dbo.samples.image.houghtransform.CaregorizerImageTracer.save;
import static de.dbo.samples.image.houghtransform.Util.applyMandatoryFilter;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.dbo.samples.image.houghtransform.api.Categorizer;
import de.dbo.samples.image.houghtransform.api.CategorizerConfiguration;
import de.dbo.samples.image.houghtransform.api.CategorizerException;
import de.dbo.samples.image.houghtransform.api.CategorizerWorker;
import de.dbo.samples.image.houghtransform.api.Category;
import de.dbo.samples.image.houghtransform.api.ImageInfo;
import de.dbo.samples.image.houghtransform.api.ShapeFilter;


/**
 * Marker categorization. The module performs classification of the marker
 * images in two steps. The first step is the shape categorization, while the
 * second step is the content categorization
 *
 * @see Category
 *
 * @author D.Boulanger Hombach
 */
public class CategorizerImpl implements Categorizer {
    private static final Logger      log                = LoggerFactory.getLogger(CategorizerImpl.class);

    protected CategorizerWorker   shapeCategorizer   = null;
    protected CategorizerWorker   contentCategorizer = null;
    protected BufferedImage          shapeFilteredImage = null;
    protected ShapeFilter         shapeFilter        = null;

    private final ApplicationContext ctx;
    private final String             ctxname;

    private final String             bean               = "shapeFinderConfig";
    private final String             bean2              = "contentProcessorConfig";

    private CategorizerConfiguration cfg                = null;
    private CategorizerConfiguration cfg2               = null;

    /**
     * @param ctxname
     *            name of the XML-file (Spring resource)
     * @throws CategorizerException
     */
    public CategorizerImpl(final String ctxname) throws CategorizerException {
        try {
            this.ctxname = ctxname;
            this.ctx = new ClassPathXmlApplicationContext(ctxname);
        }
        catch(Exception exception) {
            throw new CategorizerException(CategorizerException.BEANS_CTX_INITILIZATION,
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
    public CategorizerImpl(final String ctxname, final ApplicationContext ctx) {
        this.ctxname = ctxname;
        this.ctx = ctx;
    }

    private static final Rectangle whiteBorder(final BufferedImage image, double delta) {
    	 if (0.01 > delta) {
             return null;
         }
         final int deltaX =  (int) ( (image.getWidth() )  * delta );
         final int deltaY =  (int) ( (image.getHeight() ) * delta );
         return new Rectangle(deltaX, deltaY,
        		 image.getWidth() - 4 * deltaX, image.getHeight() - 2 * deltaY);
    }

    public static final BufferedImage applyWhiteBorder(final BufferedImage image, final CategorizerConfiguration cfg) {
        final Rectangle border = whiteBorder(image,cfg.getWhiteBorder());
        if (null==border) {
        	return image;
        }

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
    public final Category getCategory(final BufferedImage imageOrigin) throws CategorizerException {
    	final BufferedImage image =  applyMandatoryFilter(imageOrigin);
        try {
            cfg = (CategorizerConfiguration) this.ctx.getBean(bean);
        }
        catch(BeansException e) {
            throw new CategorizerException(CategorizerException.BEANS_CONTENT_INITILIZATION,
                    "Failure while creating " + bean + " config-bean from the Spring-CTX " + this.ctxname, e);
        }

        if (cfg.isEnabled()) {
            shapeCategorizer = getCategorizerWorker(image, null, cfg);
            if (!shapeCategorizer.isShapeFound()) {
                return save(image,Category.UNKNOWN);
            }
            if (!cfg.shapeFilterUsage()) {
                return save(image,shapeCategorizer.category());
            }
            if (!shapeCategorizer.isShapeFilterWelldefined()) {
                return save(image,shapeCategorizer.category());
            }
        }

        // run the second phase using the shape-filtered image
        // and use its results for the final categorization
        try {
            cfg2 = (CategorizerConfiguration) this.ctx.getBean(bean2);
        }
        catch(BeansException e) {
            throw new CategorizerException(CategorizerException.BEANS_CONTENT_INITILIZATION,
                    "Failure while creating " + bean2 + " config-bean from the Spring-CTX " + this.ctxname, e);
        }
        if (cfg.isEnabled()) {
            shapeFilter = shapeCategorizer.getShapeFilter();
            shapeFilteredImage = shapeCategorizer.getShapeCroppedImage();
            contentCategorizer = getCategorizerWorker(shapeFilteredImage, null, cfg2);
            return save(image,contentCategorizer.category());
        }
        else {
            contentCategorizer = getCategorizerWorker(applyWhiteBorder(image, cfg2), null, cfg2);
            return save(image,contentCategorizer.category());
        }
    }

    protected CategorizerWorker getCategorizerWorker(final BufferedImage image, final ImageInfo info, final CategorizerConfiguration cfg)
            throws CategorizerException {
        return new CategorizerWorkerImpl(image, cfg);
    }

}
