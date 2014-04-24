package de.dbo.samples.gui.swing.treetable.impl.ref;

import static de.dbo.samples.gui.swing.treetable.api.WindowTools.elapsed;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.gbc1xManyLeft;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.gbc1xManyRight;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.gbl1xMany;

import de.dbo.samples.gui.swing.treetable.api.Window;
import de.dbo.samples.gui.swing.treetable.api.factory.Factory;
import de.dbo.samples.gui.swing.treetable.api.factory.FactoryManager;

import javax.swing.JMenuBar;

abstract class RecordsWindowAbstraction extends Window {
	protected static final long serialVersionUID = 4489500964556705612L;
	
	public static final Factory factory(final String ctx) {
		final long start = System.currentTimeMillis();
        final Factory factory = FactoryManager.instance(ctx);
        elapsed(start, "creating tree-table factory" );
        return factory;
	}
	
	/* treetable factory and record provider */
	private final Factory factory;
	
    private final TreetablePane treetablePane;
    
	/**
	 * GUI with childless treetable-root.
	 * Initial status is UNLOCKED, records = null
	 */
	protected RecordsWindowAbstraction(final String ctx, final String title) {
        super(title + " - " + ctx);
        factory = factory(ctx);
        treetablePane = new TreetablePane(factory);
       
        final JMenuBar jMenuBar =  new JMenuBar();
        jMenuBar.setLayout(gbl1xMany());
        int x = 0;
        jMenuBar.add(treetablePane.getControlsPane(),gbc1xManyLeft(x++,0));
        jMenuBar.add(treetablePane.getStatusPane(),gbc1xManyRight(x++,0));

        // frame
        setJMenuBar(jMenuBar);
        setContentAs1x1(treetablePane);
      
    }
	
	
}