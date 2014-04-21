package de.dbo.samples.gui.swing.treetable.impl.ref;

import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordProviderAbstraction;
import de.dbo.samples.gui.swing.treetable.impl.ref.data.Records;
import de.dbo.samples.gui.swing.treetable.impl.ref.data.Records1;
import de.dbo.samples.gui.swing.treetable.impl.ref.data.Records2;

import java.util.List;

public class RecordProviderImpl extends RecordProviderAbstraction {
	 
 protected final List<Record> getRecords() {
		if (!nn(transaction)) {
			return null;
		}

		final String transactionTrimmed = transaction.trim();
		if ("0".equals(transactionTrimmed)) {
			return new Records();
		} else if ("1".equals(transactionTrimmed)) {
			return new Records1();
		} else if ("2".equals(transactionTrimmed)) {
			return new Records2();
		} else {
			return null;
		}
	}
}
