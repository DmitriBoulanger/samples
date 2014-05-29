package de.dbo.samples.image.houghtransform.core;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public interface Constants {

    static final Color         SHAPE_COLOR       = new Color(255, 191, 0);
    static final Color         UNKNOWN_COLOR     = new Color(250, 250, 250);
    static final Color         CONTENT_COLOR     = new Color(234, 10, 55);
    
    static final int           WHITE_COLOR_RGB   = Color.WHITE.getRGB();
    static final int           BLACK_COLOR_RGB   = Color.BLACK.getRGB();
    
    static final int           SHAPE_COLOR_RGB   = SHAPE_COLOR.getRGB();
    static final int           CONTENT_COLOR_RGB = CONTENT_COLOR.getRGB();
    static final int           UNKNOWN_COLOR_RGB = WHITE_COLOR_RGB; //UNKNOWN_COLOR.getRGB();

    
   
    static final double        SQRT2             = Math.sqrt(2);
    static final double        SQRT22            = SQRT2 / 2.0D;
    static final double        PI                = Math.PI;
    static final double        PI2               = PI / 2.0D;
    static final double        PI4               = PI2 / 2.0D;
    static final double        PI4x3             = PI4 * 3.0D;

    static final double        ZERO              = 0.0D;
    static final double        ONE               = 1.0D;
    static final double        TEN               = 10.0D;
    static final double        HUNDRED           = 100.0D;
    static final double        THOUSAND          = 1000.0D;

    static final NumberFormat  THETA_FORMAT      = new DecimalFormat("0.000");
    static final DecimalFormat RADIUS_FORMAT     = new DecimalFormat("000");
    static final DecimalFormat PIXEL_FORMAT      = new DecimalFormat("000");
}
