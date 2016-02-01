package de.dbo.samples.gui.swing.treetable.origins.xexamples.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.CompoundPainter;
import org.jdesktop.swingx.painter.GlossPainter;
import org.jdesktop.swingx.painter.MattePainter;
import org.jdesktop.swingx.painter.Painter;
import org.jdesktop.swingx.painter.PinstripePainter;

/**
 * PainterExample1
 * 
 * @author Nazmul Idris
 * @version 1.0
 * @since Dec 25, 2007, 3:54:13 PM
 */
public class PainterExample1 {

	/**
	 * simple main driver for this class
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PainterExample1();
			}
		});
	}

	/**
	 * creates a JFrame and calls {@link #doInit} to create a JXPanel and adds
	 * the panel to this frame.
	 */
	public PainterExample1() {
		JFrame frame = new JFrame("Painter Example 1");

		// add the panel to this frame
		frame.add(doInit());

		// when you close the frame, the app exits
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// center the frame and show it
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * creates a JXLabel and attaches a painter to it.
	 */
	private Component doInit() {
		JXPanel panel = new JXPanel();
		panel.setLayout(new BorderLayout());

		JXLabel label = new JXLabel();
		label.setFont(new Font("Segoe UI", Font.BOLD, 14));
		label.setText("Painter Example 1");
		label.setHorizontalAlignment(JXLabel.CENTER);
		label.setBackgroundPainter(getPainter());

		panel.add(label, BorderLayout.CENTER);

		panel.setPreferredSize(new Dimension(250, 100));

		return panel;
	}

	/**
	 * this compound painter composites a bunch of different painters.
	 */
	private Painter<?> getPainter() {
		// set the background painter
		MattePainter mp = new MattePainter(Colors.LightBlue.alpha(0.5f));
		GlossPainter gp = new GlossPainter(Colors.White.alpha(0.3f),
				GlossPainter.GlossPosition.TOP);
		PinstripePainter pp = new PinstripePainter(Colors.Gray.alpha(0.2f), 45d);
		return (new CompoundPainter<Painter<?>>(mp, pp, gp));
	}

}// end class PainterExample1
