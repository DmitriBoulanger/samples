package de.dbo.samples.gui.swing.treetable.impl.ref;

import de.dbo.samples.gui.swing.treetable.impl.Window;

import javax.swing.SwingUtilities;

public final class RecordsWindow extends Window {
	private static final long serialVersionUID = 4489500964556705612L;
	
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				setLookAndFeel();
				new RecordsWindow().showup();
			}
		});
	}
	
	/**
	 * GUI with childless treetable-root.
	 * Initial status is UNLOCKED, records = null
	 */
	RecordsWindow() {
        super("ReferenceImplementation.xml", "Tree-Table with Records");
    }
	
	
}