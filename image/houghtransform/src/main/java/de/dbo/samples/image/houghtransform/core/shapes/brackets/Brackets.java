package de.dbo.samples.image.houghtransform.core.shapes.brackets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import de.dbo.samples.image.houghtransform.core.Constants;
import de.dbo.samples.image.houghtransform.core.hough.HoughShape;

public final class Brackets extends HoughShape implements Constants {

    public Brackets(Double rectangularRatio) {
        super(rectangularRatio);
    }

    @Override
    public boolean isWelldefined() {
        return isBorderComplete() && isRectangularWellDefined();
    }

    private boolean isRectangularWellDefined() {
        return error() <= rectangularRatio;
    }

    @Override
    public void draw(Graphics g, Color ok, Color bad, Point postion) {
        g.setColor(isWelldefined() ? ok : bad);
        final int x = postion.x + this.left;
        final int y = postion.y + this.top;
        g.drawLine(x, y, x, y + height());
        g.drawLine(x + width(), y, x + width(), y + height());

        g.drawLine(x - 1, y, x - 1, y + height());
        g.drawLine(x + width() - 1, y, x + width() - 1, y + height());

        g.drawLine(x + 1, y, x + 1, y + height());
        g.drawLine(x + width() + 1, y, x + width() + 1, y + height());
    }

}
