package de.dbo.samples.pico.simple.window;

import javax.swing.JFrame;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;

public class MainModule {
    public static PicoContainer newContainer() {
        final MutablePicoContainer pico = new DefaultPicoContainer();
        pico.addComponent(PicoContainer.class, pico);
        pico.addComponent(MainWindow.class);
        pico.addComponent(JFrame.class);
        pico.addComponent(InfoWindowProvider.class);
        pico.addComponent(ShowInfoWindowAction.class);
        pico.addComponent(ShowInfoWindowButton.class);
        return pico;
    }
}
