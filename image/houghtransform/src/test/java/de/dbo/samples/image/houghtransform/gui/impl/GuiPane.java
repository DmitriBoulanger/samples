package de.dbo.samples.image.houghtransform.gui.impl;

import de.dbo.samples.image.houghtransform.api.Category;
import de.dbo.samples.image.houghtransform.api.ImageInfo;
import de.dbo.samples.image.houghtransform.api.Shape;
import de.dbo.samples.image.houghtransform.core.Constants;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Vector;

final class GuiPane extends JPanel implements Constants {
    private static final long serialVersionUID = 1695601968261320308L;

    private BufferedImage     image            = null;
    private Shape          shape            = null;
    private Vector<String>    text             = null;
    private ImageInfo      imageInfo        = null;
    private Category       category         = null;
    private Category       categoryExpected = null;

    GuiPane() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawImage(g);
        drawShape(g);
        drawText(g);
    }

    void mydraw(BufferedImage image) {
        mydraw(image, null);
    }

    void mydraw(BufferedImage image, Shape shape) {
        this.shape = shape;
        this.image = image;
        repaint();
    }

    void mydraw(Vector<String> text) {
        this.text = text;
        repaint();
    }

    void mydraw(Category category, ImageInfo info) {
        this.imageInfo = info;
        this.category = category;
        this.categoryExpected = info.category;
        this.text = new Vector<String>();
        this.text.add(category.name());
        this.text.add(categoryExpected.name().toLowerCase());
        repaint();
    }

    private void drawShape(final Graphics g) {
        if (null == this.shape) {
            return;
        }
        this.shape.draw(g, SHAPE_COLOR, UNKNOWN_COLOR, new Point(0, 0));

    }

    private void drawImage(final Graphics g) {
        if (null == this.image) {
            return;
        }
        g.drawImage(this.image, 0, 0, null);
    }

    private void drawText(final Graphics g) {
        if (null == this.text || 0 == this.text.size()) {
            return;
        }
        optChangeColor();
        final Font font = new Font(g.getFont().getFamily(), Font.BOLD, 11);
        g.setFont(font);
        int i = 0;
        for (String line : this.text) {
            i++;
            g.drawString(line, 0, 11 * i);
        }
    }

    private static final Color GREEN = new Color(27, 206, 14);
    private static final Color RED   = new Color(247, 32, 32);
    private static final Color GRAY  = Color.GRAY;

    private final void optChangeColor() {
        if (null == this.category || null == this.categoryExpected) {
            return;
        }
        if (!imageInfo.junit) {
            setForeground(UNKNOWN_COLOR);
        }
        else if (this.category == this.categoryExpected) {
            setForeground(GREEN);
        }
        else if (Category.UNKNOWN == this.categoryExpected) {
            setForeground(GRAY);
        }
        else {
            setForeground(RED);
        }
    }
}
