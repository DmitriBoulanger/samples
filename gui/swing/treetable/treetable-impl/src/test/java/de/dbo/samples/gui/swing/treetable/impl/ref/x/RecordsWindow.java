package de.dbo.samples.gui.swing.treetable.impl.ref.x;

import de.dbo.samples.gui.swing.treetable.impl.Window;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public final class RecordsWindow extends Window {
	private static final long serialVersionUID = 4489500964556705612L;
	
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					log.error("Can't set the system Look-and-Feel", e);
				}
				new RecordsWindow().showup(0.6D);
			}
		});
	}
	
	/**
	 * GUI with childless treetable-root.
	 * Initial status is UNLOCKED, records = null
	 */
	RecordsWindow() {
        super("XReferenceImplementation.xml"
        , "XTreetable with test-records - System LookAndFeel");
    }
	
	
}