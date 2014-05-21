package de.dbo.samples.image.houghtransform.testimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import de.dbo.samples.image.houghtransform.ImageCollections;
import de.dbo.samples.image.houghtransform.ImageProvider;
import de.dbo.samples.image.houghtransform.api.OMRImageInfo;

public abstract class GuiShowUp {

    protected static final void showUp(final ImageCollections collection, final String ctxname, final ApplicationContext ctx)
            throws Exception {

        final ImageProvider imageProvider = new ImageProvider(collection);
        final List<CategorizerGui> categorizers = new ArrayList<CategorizerGui>();
        for (final OMRImageInfo info : imageProvider.getImageInfos()) {
            categorizers.add(new CategorizerGui(imageProvider, info, ctxname, ctx));
        }

        new Gui(imageProvider, ctxname, ctx, categorizers).showUp();
    }

    protected static final void showUpSignature(final ImageCollections collection, final String ctxname, final ApplicationContext ctx)
            throws Exception {

        final ImageProvider imageProvider = new ImageProvider(collection);
        final List<CategorizerGui> categorizers = new ArrayList<CategorizerGui>();
        for (final OMRImageInfo info : imageProvider.getImageInfos()) {
            categorizers.add(new CategorizerGui(imageProvider, info, ctxname, ctx));
        }

        new GuiSignature(imageProvider, ctxname, ctx, categorizers).showUp();
    }

}
