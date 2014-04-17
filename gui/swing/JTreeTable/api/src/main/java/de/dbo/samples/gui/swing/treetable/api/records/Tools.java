package de.dbo.samples.gui.swing.treetable.api.records;

import java.text.DecimalFormat;
import java.util.List;

public final class Tools {
	
	private Tools()   {
		// never initialize it as an instance
	}
	
	public final static void extractRecords(final Node node, final List<Record> records) {
		final List<Node> children = node.getChildren();
		if (children.isEmpty()) {
			final Record record = (Record) node.getContents();
			if (null!=record) {
				records.add(record);
			} 
			return;
		}
		
		for (final Node child:children) {
			final List<Node> children2 = child.getChildren();
			if (children2.isEmpty()) {
				final Record record = (Record) child.getContents();
				if (null!=record) {
					records.add(record);
				} 
			} else {
				for (final Node child2:children2) {
					extractRecords(child2,records);
				}
			}
		}
		
	}
	
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
				for (Record record : recordList.getRecords()) {
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
	
	static final boolean nn(final String x) {
		return null!=x && 0!=x.trim().length();
	}
}
