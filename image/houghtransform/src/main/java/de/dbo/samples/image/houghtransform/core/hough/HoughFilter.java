package de.dbo.samples.image.houghtransform.core.hough;

import java.awt.Rectangle;

import com.jhlabs.image.WholeImageFilter;

import de.dbo.samples.image.houghtransform.api.OMRShape;
import de.dbo.samples.image.houghtransform.api.OMRShapeFilter;

/**
 * Abstract filter which drops all pixels outside the shape.
 */

public abstract class HoughFilter extends WholeImageFilter implements OMRShapeFilter, Constants {

    protected final Rectangle rectangle;

    protected HoughFilter(final OMRShape shape, Integer marginprocent) {
        final Rectangle shapeRectangular = shape.getRectangle();
        final double diagonal = Math.sqrt(shapeRectangular.width * shapeRectangular.width + shapeRectangular.height * shapeRectangular.height);
        final int margin = (int) (diagonal * ((marginprocent.intValue()) / HUNDRED));
        this.rectangle = new Rectangle();
        this.rectangle.setLocation(shapeRectangular.x + margin, shapeRectangular.y + margin);
        this.rectangle.setSize(shapeRectangular.width - 2 * margin, shapeRectangular.height - 2 * margin);
    }

    @Override
    public final Rectangle getRectangle() {
        return rectangle;
    }
}
