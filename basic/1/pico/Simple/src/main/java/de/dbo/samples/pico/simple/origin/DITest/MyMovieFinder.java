package de.dbo.samples.pico.simple.origin.DITest;

import org.picocontainer.*;
import org.picocontainer.*;
import java.awt.List;

class MyMovieFinder {

    private MutablePicoContainer configureContainer() {
		MutablePicoContainer pico = new DefaultPicoContainer();
		pico.addComponent(MovieFinderImpl.class);
		pico.addComponent(MovieLister.class);
        return pico;
    }

    public void testWithPico() {
        MutablePicoContainer pico = configureContainer();
        MovieLister lister = (MovieLister) pico.addComponent(MovieLister.class);
        lister.listFinder();
    }

}