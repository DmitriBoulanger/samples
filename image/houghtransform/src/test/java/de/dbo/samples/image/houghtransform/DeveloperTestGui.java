package de.dbo.samples.image.houghtransform;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.dbo.samples.image.houghtransform.testimpl.GuiShowUp;

public final class DeveloperTestGui extends GuiShowUp {

    public static void main(String[] args) throws Exception {

        final String CTX = "omr-circle-cfg.xml";
        final ApplicationContext ctx = new ClassPathXmlApplicationContext(CTX);
        //        showUp(ImageCollections.CIRCLE_TEST0, CTX, ctx);
        //        showUp(ImageCollections.CIRCLE_SAMPLES_NORMAL0, CTX, ctx);

    }
}
