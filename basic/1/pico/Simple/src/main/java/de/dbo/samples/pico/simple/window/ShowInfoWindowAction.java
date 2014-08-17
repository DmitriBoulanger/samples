package de.dbo.samples.pico.simple.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowInfoWindowAction implements ActionListener {
    private static int n;
    private final InfoWindowProvider provider;

    public ShowInfoWindowAction(InfoWindowProvider provider) {
        this.provider = provider;
    }

    @Override public void actionPerformed(ActionEvent e) {
        InfoWindow infoWindow = provider.get(++n);
        infoWindow.show();
    }
}
