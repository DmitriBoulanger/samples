package de.dbo.samples.gui.swing.treetable.origins.xexamples.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LinearGradientPaint;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;
import org.jdesktop.swingx.painter.MattePainter;
import org.jdesktop.swingx.painter.Painter;

/**
 * TaskPaneExample1
 * 
 * @author Nazmul Idris
 * @version 1.0
 * @since Jan 28, 2008, 12:49:01 PM
 */
public class TaskPaneExample1 {

	/** simple main driver for this class */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TaskPaneExample1();
			}
		});
	}

	/**
	 * creates a JFrame and calls {@link #doInit} to create a JXPanel and adds
	 * the panel to this frame.
	 */
	public TaskPaneExample1() {
		JFrame frame = new JFrame("TaskPane Example 1");

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

		// create a label
		final JXLabel label = new JXLabel();
		label.setFont(new Font("Segoe UI", Font.BOLD, 14));
		label.setText("task pane item 1 : a label");
		label.setIcon(Images.NetworkDisconnected.getIcon(32, 32));
		label.setHorizontalAlignment(JXLabel.LEFT);
		label.setBackgroundPainter(getPainter());

		// tweak with the UI defaults for the taskpane and taskpanecontainer
		changeUIdefaults();

		// create a taskpanecontainer
		JXTaskPaneContainer taskpanecontainer = new JXTaskPaneContainer();

		// create a taskpane, and set it's title and icon
		JXTaskPane taskpane = new JXTaskPane();
		taskpane.setTitle("My Tasks");
		taskpane.setIcon(Images.Quit.getIcon(24, 24));

		// add various actions and components to the taskpane
		taskpane.add(label);
		taskpane.add(new AbstractAction() {
			private static final long serialVersionUID = -7314920635669764914L;
			{
				putValue(Action.NAME, "task pane item 2 : an action");
				putValue(Action.SHORT_DESCRIPTION, "perform an action");
				putValue(Action.SMALL_ICON,
						Images.NetworkConnected.getIcon(32, 32));
			}

			public void actionPerformed(ActionEvent e) {
				label.setText("an action performed");
			}
		});

		// add the task pane to the taskpanecontainer
		taskpanecontainer.add(taskpane);

		// set the transparency of the JXPanel to 50% transparent
		panel.setAlpha(0.7f);

		panel.add(taskpanecontainer, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(250, 200));

		return panel;
	}

	private void changeUIdefaults() {
		// JXTaskPaneContainer settings (developer defaults)
		/*
		 * These are all the properties that can be set (may change with new
		 * version of SwingX) "TaskPaneContainer.useGradient",
		 * "TaskPaneContainer.background",
		 * "TaskPaneContainer.backgroundGradientStart",
		 * "TaskPaneContainer.backgroundGradientEnd", etc.
		 */

		// setting taskpanecontainer defaults
		UIManager.put("TaskPaneContainer.useGradient", Boolean.FALSE);
		UIManager.put("TaskPaneContainer.background",
				Colors.LightGray.color(0.5f));

		// setting taskpane defaults
		UIManager.put("TaskPane.font", new FontUIResource(new Font("Verdana",
				Font.BOLD, 16)));
		UIManager.put("TaskPane.titleBackgroundGradientStart",
				Colors.White.color());
		UIManager.put("TaskPane.titleBackgroundGradientEnd",
				Colors.LightBlue.color());
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

}// end class TaskPaneExample1
