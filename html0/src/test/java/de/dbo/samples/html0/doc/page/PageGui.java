package de.dbo.samples.html0.doc.page;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class PageGui {

	// Use the event dispatch thread for Swing components
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new PageGui("Page GUI"
						, new Dimension(600, 600) /* default pane size */);
			}
		});
	}

	public PageGui(final String title, final Dimension size) {
		final JPanel pane = pane(title,size);
		pane.setPreferredSize(size);
		final JFrame frame = frame(title,size,40);
		final JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(pane);
		frame.add(jScrollPane, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	private static final JFrame frame(final String title, final Dimension size, int margin) {
		final JFrame ret = new JFrame();
		ret.setTitle(title);
		ret.setSize(new Dimension(size.width+margin, size.height+margin));

		// Center the frame in the middle of the screen
		ret.setLocationRelativeTo(null);
		ret.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return ret;
	}
	
	private static final JPanel pane(final String title, final Dimension size) {
		final GridBagLayout grid = new GridBagLayout();
		grid.columnWidths = new int[] { 0 };
		grid.columnWeights = new double[] { 0 };
		grid.rowHeights = new int[] { 0 };
		grid.rowWeights = new double[] { 0 };
		
		final int inset = 20;

		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.ipadx = 10;
		gbc.ipady = 10;
		
		final JPanel pane = new JPanel();
		pane.setLayout(grid);

		final Dimension labelSize = new Dimension(size.width-2*inset, size.height-2*inset);
		final JLabel label = new JLabel();
		label.setPreferredSize(labelSize);
		label.setBorder(new EmptyBorder(new Insets(inset, inset, inset, inset)) );
		
		final PageInstance page = new PageInstance(false,false);
		try {
			page.save();
		} catch (Exception e) {
			e.printStackTrace();
		}
		label.setText(page.toString());
		
		pane.add(label, gbc);

		return pane;
	}

}
