package de.dbo.samples.html0.doc.page;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class PageGui {
	
	private static final Dimension FRAME_SIZE = new Dimension(950, 800);
	private static final Dimension LABEL_SIZE = new Dimension(700, 700);
	
	// Use the event dispatch thread for Swing components
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new PageGui();
			}
		});
	}

	private static final JPanel label() {
		final PageInstance page = 
				new PageInstance(LABEL_SIZE.width+"px",false,false);
		try {
			new PageInstance(LABEL_SIZE.width+"px",true,true).save();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setText(page.toString());
		
		final GridBagLayout grid = new GridBagLayout();
		grid.columnWidths = new int[] { 0 ,0};
		grid.columnWeights = new double[] { 0,0 };
		grid.rowHeights = new int[] { 0,0 };
		grid.rowWeights = new double[] { 0,0 };
		
		final GridBagConstraints gbcLabel = new GridBagConstraints();
		gbcLabel.anchor = GridBagConstraints.NORTHWEST;
		gbcLabel.gridx = 0;
		gbcLabel.gridy = 0;
		gbcLabel.insets = new Insets(0, 0, 0, 0);
		
		final GridBagConstraints gbcEmpty = new GridBagConstraints();
		gbcEmpty.gridx = 1;
		gbcEmpty.gridy = 1;
		gbcEmpty.insets = new Insets(0, 0, 0, 0);
		gbcEmpty.gridwidth = GridBagConstraints.REMAINDER;
		gbcEmpty.fill=GridBagConstraints.BOTH;
		gbcEmpty.weightx = 1.0;
		gbcEmpty.weighty = 1.0;
		
		final JPanel pane = new JPanel();
		pane.setLayout(grid);
	    pane.add(label,gbcLabel);
	    pane.add(new JLabel("   "),gbcEmpty);
	    pane.setBorder(new LineBorder(Color.RED, 1));
	    size("Pane0", pane);
		return pane;
	}
	
	private static final JScrollPane scrollPane(final JPanel pane) {
		final JViewport viewport = new JViewport();
		viewport.setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		viewport.setView(pane);
		viewport.setBorder(null);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBar(null);
		scrollPane.setBorder(null);
		scrollPane.setViewportBorder(null);
		scrollPane.setViewport(viewport);
		
		size("Pane06",pane);
		return scrollPane;
	}
	
	public PageGui() {
	     final JPanel pane = label();
	     final JScrollPane  scrollPane = scrollPane(pane);
		size("Pane07",pane);
		showupInFrame(scrollPane);
	}
	
	private static final void showupInFrame(final JScrollPane scrollPane) {
		final JFrame ret = new JFrame();
		ret.setTitle("Frame=" + FRAME_SIZE.width + "x" + FRAME_SIZE.height);
		ret.setSize(FRAME_SIZE);
		ret.setLocationRelativeTo(null);
		ret.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ret.getContentPane().add(scrollPane);
		ret.setVisible(true);
	}
	
	private static final void size(final String name, final JComponent component) {
		System.err.println(
		 name+"="+component.getPreferredSize().toString().replaceAll("java.awt.Dimension", ""));
	}
}
