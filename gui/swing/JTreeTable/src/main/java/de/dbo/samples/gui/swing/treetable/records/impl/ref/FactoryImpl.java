package de.dbo.samples.gui.swing.treetable.records.impl.ref;

import de.dbo.samples.gui.swing.treetable.records.api.Factory;
import de.dbo.samples.gui.swing.treetable.records.api.Node;
import de.dbo.samples.gui.swing.treetable.records.api.Record;

public class FactoryImpl implements Factory {
	
	public FactoryImpl(/* context */) {
		
	}
	
	@Override
	public Node newNode(final String name, final Record record) {
		return new NodeImpl(name, record);
	}

}
