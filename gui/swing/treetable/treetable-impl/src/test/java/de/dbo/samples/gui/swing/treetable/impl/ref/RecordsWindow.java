package de.dbo.samples.gui.swing.treetable.impl.ref;

import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;

public final class RecordsWindow extends RecordsWindowAbstraction {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		 
	}
	
	
}