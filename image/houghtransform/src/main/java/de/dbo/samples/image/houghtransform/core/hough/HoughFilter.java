package de.dbo.samples.image.houghtransform.core.hough;

import static de.dbo.samples.image.houghtransform.core.Constants.HUNDRED;

import java.awt.Rectangle;

import com.jhlabs.image.WholeImageFilter;

import de.dbo.samples.image.houghtransform.api.Shape;
import de.dbo.samples.image.houghtransform.api.ShapeFilter;

/**
 * Abstract filter which drops all pixels outside the shape.
 */

public abstract class HoughFilter extends WholeImageFilter implements ShapeFilter {

    protected final Rectangle rectangle;

    protected HoughFilter(final Shape shape, Integer marginprocent) {
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
