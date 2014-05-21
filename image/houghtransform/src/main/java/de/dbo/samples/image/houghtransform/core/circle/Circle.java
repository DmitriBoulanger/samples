package de.dbo.samples.image.houghtransform.core.circle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import de.dbo.samples.image.houghtransform.core.hough.HoughShape;

public final class Circle extends HoughShape {

    public Circle(Double rectangularRatio) {
        super(rectangularRatio);
    }

    @Override
    public boolean isWelldefined() {
        return isBorderComplete() && isRectangularWelldefined();
    }

    private boolean isRectangularWelldefined() {
        return error() <= rectangularRatio;
    }

    @Override
    public void draw(Graphics g, Color ok, Color bad, Point postion) {
        g.setColor(isWelldefined() ? ok : bad);
        final int x = postion.x + this.left;
        final int y = postion.y + this.top;
        g.drawOval(x + 1, y + 1, width() + 2, height() + 2);
        g.drawOval(x, y, width(), height());
        g.drawOval(x - 1, y - 1, width() + 2, height() + 2);
    }
}
