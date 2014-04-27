package de.dbo.samples.gui.swing.treetable.origins.xexamples.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LinearGradientPaint;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.MattePainter;
import org.jdesktop.swingx.painter.Painter;

/**
 * PainterExample2
 * 
 * @author Nazmul Idris
 * @version 1.0
 * @since Dec 25, 2007, 3:54:13 PM
 */
public class PainterExample2 {

	/** simple main driver for this class */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PainterExample2();
			}
		});
	}

	/**
	 * creates a JFrame and calls {@link #doInit} to create a JXPanel and adds
	 * the panel to this frame.
	 */
	public PainterExample2() {
		JFrame frame = new JFrame("Painter Example 2");

		// add the panel to this frame
		frame.add(doInit());

		// when you close the frame, the app exits
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// center the frame and show it
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

	/** creates a JXLabel and attaches a painter to it. */
	private Component doInit() {
		JXPanel panel = new JXPanel();
		panel.setLayout(new BorderLayout());

		JXLabel label = new JXLabel();
		label.setFont(new Font("Segoe UI", Font.BOLD, 14));
		label.setText("Painter Example 2");
		label.setHorizontalAlignment(JXLabel.CENTER);
		label.setBackgroundPainter(getPainter());

		// set the transparency of the JXPanel to 50% transparent
		panel.setAlpha(0.5f);

		panel.add(label, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(250, 100));

		return panel;
	}

	/** this painter draws a gradient fill */
	public Painter<?> getPainter() {
		int width = 100;
		int height = 100;
		Color color1 = Colors.White.color(0.5f);
		Color color2 = Colors.Gray.color(0.5f);

		LinearGradientPaint gradientPaint = new LinearGradientPaint(0.0f, 0.0f,
				width, height, new float[] { 0.0f, 1.0f }, new Color[] {
						color1, color2 });
		MattePainter mattePainter = new MattePainter(gradientPaint);
		return mattePainter;
	}

}// end class PainterExample2