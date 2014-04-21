package de.dbo.samples.gui.swing.treetable.impl.ref;

import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordProviderAbstraction;
import de.dbo.samples.gui.swing.treetable.impl.ref.data.Records;
import de.dbo.samples.gui.swing.treetable.impl.ref.data.Records1;
import de.dbo.samples.gui.swing.treetable.impl.ref.data.Records2;

import java.util.ArrayList;
import java.util.List;

public class RecordProviderImpl extends RecordProviderAbstraction {
	
	final Records records = new Records();
	 
 protected final List<Record> getRecords() {
		if (!nn(transaction)) {
			return null;
		}

		final String transactionTrimmed = transaction.trim();
		final List<Record> ret = new ArrayList<Record>();
		if ("0".equals(transactionTrimmed)) {
			ret.addAll(records);
			return ret;
		} else if ("1".equals(transactionTrimmed)) {
			ret.addAll(new Records1(records));
			return ret;
		} else if ("2".equals(transactionTrimmed)) {
			ret.addAll(new Records2(records));
			return ret;
		} else if ("3".equals(transactionTrimmed)) {
			ret.addAll(records);
			return ret;
		} else {
			return null;
		}
	}
}
