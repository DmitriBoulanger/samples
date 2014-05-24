package de.dbo.samples.image.houghtransform.gui.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import de.dbo.samples.image.houghtransform.api.ImageInfo;
import de.dbo.samples.image.houghtransform.data.ImageCollections;
import de.dbo.samples.image.houghtransform.data.ImageProvider;

public abstract class GuiShowUp {

    protected static final void showUp(final ImageCollections collection, final String ctxname, final ApplicationContext ctx)
            throws Exception {

        final ImageProvider imageProvider = new ImageProvider(collection);
        final List<CategorizerGui> categorizers = new ArrayList<CategorizerGui>();
        for (final ImageInfo info : imageProvider.getImageInfos()) {
            categorizers.add(new CategorizerGui(imageProvider, info, ctxname, ctx));
        }

        new Gui(imageProvider, ctxname, ctx, categorizers).showUp();
    }

    protected static final void showUpSignature(final ImageCollections collection, final String ctxname, final ApplicationContext ctx)
            throws Exception {

        final ImageProvider imageProvider = new ImageProvider(collection);
        final List<CategorizerGui> categorizers = new ArrayList<CategorizerGui>();
        for (final ImageInfo info : imageProvider.getImageInfos()) {
            categorizers.add(new CategorizerGui(imageProvider, info, ctxname, ctx));
        }

        new GuiSignature(imageProvider, ctxname, ctx, categorizers).showUp();
    }

}
