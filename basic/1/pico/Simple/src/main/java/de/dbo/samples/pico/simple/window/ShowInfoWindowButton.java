package de.dbo.samples.pico.simple.window;

import javax.swing.JButton;

public class ShowInfoWindowButton extends JButton {
    public ShowInfoWindowButton(ShowInfoWindowAction showInfoWindowAction) {
        super("Show Info Window");
        addActionListener(showInfoWindowAction);
    }
}
