package de.dbo.samples.gui.swing.treetable.records;

import de.dbo.samples.gui.swing.treetable.records.api.Record;

import java.text.DecimalFormat;
import java.util.List;

final class Tools {
	
	final static StringBuilder printInternalData(final List<RecordTreeGenerator> children
			, final int depth, final boolean onlyNodes) {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder tab = tab(depth);
		for (RecordTreeGenerator recordList: children) {
			sb.append(tab);
			sb.append("#" + depth + "#");
			sb.append(" Node=" + recordList.node.print() + " sort=" + recordList.node.getSequence());
			if (!onlyNodes) {
				sb.append(" Records (" + recordList.size() + "):");
				for (Record record : recordList.records) {
					sb.append(print(record, depth, tab));
				}
			}
			sb.append( printInternalData(recordList.childRecordGroups, depth+1, onlyNodes));
		}
		return sb;
	}
	
	private static final StringBuilder print(final Record record
			, final int depth, final StringBuilder tab) {
		final StringBuilder sb = new StringBuilder();
			sb.append(tab);
			sb.append("SEQ="+ new DecimalFormat("00").format(record.getSequence()));
			sb.append(" path=" + record.getPath().canonicalValue());
	   return sb;
	}
	
	private static final StringBuilder tab(final int depth) {
		final StringBuilder sb = new StringBuilder("\n");
		for (int i=0; i<depth; i++) {
			sb.append("\t");
		}
		sb.append(" ");
		for (int i=0; i<depth; i++) {
			sb.append("-");
		}
		sb.append(" ");
		return sb;
	}
}
