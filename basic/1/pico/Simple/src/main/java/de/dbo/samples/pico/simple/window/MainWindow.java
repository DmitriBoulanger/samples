package de.dbo.samples.pico.simple.window;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainWindow {
    JFrame window;

    public MainWindow(JFrame window, ShowInfoWindowButton showInfoWindowButton) {
        this.window = window;
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.add(showInfoWindowButton);
    }

    public void show() {
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
