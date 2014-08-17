package de.dbo.samples.pico.simple.window;

import javax.swing.SwingUtilities;

import org.picocontainer.PicoContainer;

/**
 * see: https://github.com/avh4/picocontainer-example.git
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class Main {
    public static void main(String[] args) {
        final PicoContainer pico = MainModule.newContainer();

        SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() {
                MainWindow mainWindow = pico.getComponent(MainWindow.class);
                mainWindow.show();
            }
        });
    }
}
