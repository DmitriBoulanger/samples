package de.dbo.samples.image.houghtransform.core.shapes.signature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import de.dbo.samples.image.houghtransform.core.Constants;
import de.dbo.samples.image.houghtransform.core.hough.HoughShape;

public final class Signature extends HoughShape implements Constants {

    public Signature(Double rectangularRatio) {
        super(rectangularRatio);
    }

    @Override
    public boolean isWelldefined() {
        return true;
    }

    @Override
    public void draw(final Graphics g, final Color ok, final Color bad, final Point postion) {

    }

}
