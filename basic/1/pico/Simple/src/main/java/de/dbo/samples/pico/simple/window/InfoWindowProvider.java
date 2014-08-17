package de.dbo.samples.pico.simple.window;

import javax.swing.JFrame;

import org.picocontainer.PicoContainer;

public class InfoWindowProvider {
    private final PicoContainer pico;

    public InfoWindowProvider(PicoContainer pico) {
        this.pico = pico;
    }

    public InfoWindow get(int n) {
        final JFrame jFrame = pico.getComponent(JFrame.class);
        return new InfoWindow(n, jFrame);
    }
}
