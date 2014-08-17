package de.dbo.samples.pico.simple.window;

import javax.swing.JFrame;

public class InfoWindow {
    private JFrame window;

    public InfoWindow(int n, JFrame window) {
        this.window = window;
        window.setTitle("Info Window " + n);
    }

    public void show() {
        window.setSize(200, 100);
        window.setLocationByPlatform(true);
        window.setVisible(true);
    }
}
