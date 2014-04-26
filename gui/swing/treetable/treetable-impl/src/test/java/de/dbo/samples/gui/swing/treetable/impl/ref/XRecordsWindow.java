package de.dbo.samples.gui.swing.treetable.impl.ref;

import de.dbo.samples.gui.swing.treetable.api.XWindow;

import javax.swing.SwingUtilities;

public final class XRecordsWindow extends XWindow {
	private static final long serialVersionUID = 4489500964556705612L;
	
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
//				setLookAndFeel();
				new XRecordsWindow().showup();
			}
		});
	}
	
	/**
	 * GUI with childless treetable-root.
	 * Initial status is UNLOCKED, records = null
	 */
	XRecordsWindow() {
        super("XReferenceImplementation.xml", "XTreetable with Records");
    }
	
	
}