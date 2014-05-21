package de.dbo.samples.image.houghtransform.core.box;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import de.dbo.samples.image.houghtransform.core.hough.Constants;
import de.dbo.samples.image.houghtransform.core.hough.HoughShape;

public final class Box extends HoughShape implements Constants {

    public Box(Double rectangularRatio) {
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
        g.drawRect(x + 1, y + 1, width() - 2, height() - 2);
        g.drawRect(x, y, width(), height());
        g.drawRect(x - 1, y - 1, width() + 2, height() + 2);
    }

}
