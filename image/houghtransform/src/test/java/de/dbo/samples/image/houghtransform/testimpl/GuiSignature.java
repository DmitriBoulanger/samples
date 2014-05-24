package de.dbo.samples.image.houghtransform.testimpl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import de.dbo.samples.image.houghtransform.api.CategorizerConfiguration;
import de.dbo.samples.image.houghtransform.data.ImageProvider;

/**
 * GUI to visualize HT-transformations and categorization of check-boxes. 
 * It is only used for tests and development
 *
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
final class GuiSignature extends JFrame {
    private static final long          serialVersionUID  = -1252815734610029948L;
    private static final Logger        log               = LoggerFactory.getLogger(GuiSignature.class);

    private static final int           IMAGE_GRID_WIDTH  = 8;
    private static final int           IMAGE_GRID_HEIGHT = 8;

    private final JPanel               mainPane          = new JPanel();
    private static final Color         BACKGROUND        = new Color(230, 230, 230);
    private static final double        WEIGHT_X          = 1.0;
    private static final double        WEIGHT_Y          = 1.0;
    private static final BufferedImage ICON              = getIcon();
    private static final Rectangle     MAX_RECTANGLE     = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    private static final double        MAX_RECTANGLE_FACTOR = 1.30D;

    private final ImageProvider        imageProvider;
    private final ApplicationContext   ctx;
    private final String               ctxname;

    private final List<CategorizerGui> cats;

    GuiSignature(final ImageProvider imageProvider, String ctxname, final ApplicationContext ctx, final List<CategorizerGui> cats)
            throws Exception {
        this.imageProvider = imageProvider;
        this.ctx = ctx;
        this.ctxname = ctxname;
        this.cats = cats;
    }

    final void showUp() throws Exception {
        super.setTitle(title());
        super.setIconImage(ICON);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBackground(BACKGROUND);
        super.setBounds(MAX_RECTANGLE);
        super.setAlwaysOnTop(true);
        super.setResizable(true);
        this.allocate();
        super.setVisible(true);
    }

    private final String title() {
        final CategorizerConfiguration cfg2 = (CategorizerConfiguration) this.ctx.getBean("contentProcessorConfig");
        final StringBuilder ret = new StringBuilder();
        ret.append(" Collection=" + this.imageProvider.printDataCollectionName() + "   " + "  Cfg=" + ctxname.replaceAll(".xml", ""));

        ret.append("   MaxTheta: " + cfg2.getMaxTheta() + " values ");
        ret.append("   Threshold: " + cfg2.getThreshold() + "%");
        ret.append("   WhiteBorder: " + cfg2.getWhiteBorder());
        //ret.append("   Radius: " + cfg.getShapeRadius() + "/" + cfg2.getShapeRadius());
        // ret.append( "   Neighbourhood: " + cfg.printNeighbourhoodSizes() +  "/" + cfg2.printNeighbourhoodSizes() +" px");
        ret.append("   Image Filters: " + cfg2.printImageFilters());
        ret.append("   Shape Filter: "  + cfg2.printShapeFilter());
        ret.append("   Image quality: " + cfg2.printImageQualityClassification());

        ret.append("   Contents: min=" + cfg2.getContentLineCntMin() + " total=" + cfg2.getContentLineCntTotal());

        return ret.toString();
    }

    private static final Dimension factor(final Rectangle r) {
        return (new Dimension((int) (MAX_RECTANGLE_FACTOR * r.getSize().width), (int) MAX_RECTANGLE_FACTOR * r.getSize().height));
    }

    private final void allocate() throws Exception {
        mainPane.setLayout(new GridBagLayout());
        mainPane.setMinimumSize(factor(MAX_RECTANGLE));
        for (final CategorizerGui transformer : this.cats) {
            final GuiRow row2 = new GuiRow();
            addImageRow2(row2);
            drawImageRow(transformer, row2);
        }
        final JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(mainPane);
        jScrollPane.getViewport().setPreferredSize(factor(MAX_RECTANGLE));

        this.getContentPane().add(jScrollPane);
    }

    private final void addImageRow2(GuiRow row) {
        final GridBagConstraints gbc = gbConstraints();
        addtoParent(row.category, gbc, 0.25, " Discovered and expected categories of the marker ");
        addtoParent(row.shapeFilteredImage, gbc, 4, " Image after shape-filter ");
        addtoParent(row.contentImageLines, gbc, 4, " Final image with hough-lines ");
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        addtoParent(row.contentDescription, gbc, 1.5, " Description of the hough-parameters ");
    }

    private final void addtoParent(final JPanel pane, final GridBagConstraints gbConstraints, double factor, String tip) {
        gbConstraints.weightx = WEIGHT_X * factor;
        pane.setToolTipText(tip);
        mainPane.add(pane, gbConstraints);
        gbConstraints.weightx = WEIGHT_X;
    }

    private static final GridBagConstraints gbConstraints() {
        final GridBagConstraints gbConstraints = new GridBagConstraints();
        gbConstraints.fill = GridBagConstraints.BOTH;
        gbConstraints.gridheight = IMAGE_GRID_HEIGHT;
        gbConstraints.gridwidth = IMAGE_GRID_WIDTH;
        gbConstraints.insets = new Insets(3, 3, 3, 3); // top padding
        gbConstraints.weightx = WEIGHT_X;
        gbConstraints.weighty = WEIGHT_Y;
        return gbConstraints;
    }

    private static final void drawImageRow(final CategorizerGui transformer, final GuiRow row) throws Exception {
        row.category.mydraw(transformer.category(), transformer.info());

        //        // shape phase
        //        row.sourceImage.mydraw(transformer.image());
        //        row.sourceImageFilteredWithShape.mydraw(transformer.imageFiltered(), transformer.getShape());
        //        row.paneHoughArray.mydraw(transformer.getHoughArrayImage());
        //        row.shapeDescription.mydraw(transformer.shapeDescription());

        // content phase
        row.shapeFilteredImage.mydraw(transformer.getBoxFilterdImage());
        row.contentImageLines.mydraw(transformer.imageFilteredWithLines());
        row.contentDescription.mydraw(transformer.contentDescription());
        row.paneHoughArray2.mydraw(transformer.getHoughArrayImage2());
    }

    private static final BufferedImage getIcon() {
        final String resource = "icon.png";
        InputStream is = null;
        try {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(resource);
            return javax.imageio.ImageIO.read(is);
        }
        catch(Exception e) {
            log.error("while reading " + resource, e);
            return null;
        }
        finally {
            if (null != is) {
                try {
                    is.close();
                }
                catch(IOException e) {
                    log.error("while closing stream from " + resource, e);
                }
            }
        }
    }
}
