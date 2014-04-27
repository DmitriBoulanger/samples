package de.dbo.samples.gui.swing.treetable.impl.ref;

import de.dbo.samples.gui.swing.treetable.impl.Window;

import javax.swing.SwingUtilities;

public final class RecordsWindow2 extends Window {
	private static final long serialVersionUID = 4489500964556705612L;
	
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					javax.swing.UIManager
						.setLookAndFeel(
								javax.swing.UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					log.error("Can't set system Look-and-Feel", e);
				}
				new RecordsWindow2().showup(0.6);
			}
		});
	}
	
	/**
	 * GUI with childless treetable-root.
	 * Initial status is UNLOCKED, records = null
	 */
	RecordsWindow2() {
		super("ReferenceImplementation2.xml", 
				"Treetable 2 with test-records - System LookAndFeel");
    }
}