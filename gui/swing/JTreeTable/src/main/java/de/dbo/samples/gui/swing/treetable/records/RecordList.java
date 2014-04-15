package de.dbo.samples.gui.swing.treetable.records;

import java.util.ArrayList;
import java.util.List;

public class RecordList {
	
	final List<RecordList> childRecordGroups = new ArrayList<RecordList>();
	private final List<Record> records = new ArrayList<Record>();
	
	private final int depth;
	
	/*
	 * node-name to appear in a tree
	 */
	private final String node;
	
	public RecordList() {
		this.depth = 0;
		this.node = null;
	}
	
	/**
	 * internal (recursive) constructor 
	 * @param depth
	 * @param node
	 */
	RecordList(int depth, String name) {
		this.depth = depth;
		 this.node = name;
	}
	

	public boolean add(Record record) {
		record.setSequence(records.size());
		return records.add(record);
	}
	
	public int size() {
		return records.size();
	}
	
	final void process() {
		String group = null;
		RecordList groupList = null;
		for (Record record:records) {
			final Path path = record.getPath();
			final String group2 = path.root(depth);
			if (null==group2) {
				continue;
			}
			
			if (group2.equals(group)) {
				groupList.add(record);
			} else {
				group = group2;
				groupList = new RecordList(depth+1,group);
				groupList.add(record);
				childRecordGroups.add(groupList);
			}
		}
		
		if (!childRecordGroups.isEmpty() ) {
			for (RecordList recordList:childRecordGroups) {
				recordList.process();
			}
		}
		
		
	}
	
	public StringBuilder print() {
		return print(this.childRecordGroups,0);
	}
	
	private static StringBuilder print(List<RecordList> children, int depth ) {
		final StringBuilder t = t(depth);
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for (RecordList recordList: children) {
			sb.append(t);
			sb.append("#" + depth + "#");
			sb.append(" NAME=" + recordList.node);
			sb.append(" childrenCnt=" + recordList.size() + ":");
			for (Record record: recordList.records) {
				sb.append(print(record, depth, t) );
			}
			sb.append( print(recordList.childRecordGroups, depth+1));
		}
		return sb;
	}
	
	private static final StringBuilder print(Record record, int depth, StringBuilder t) {
		final StringBuilder sb = new StringBuilder();
			sb.append(t);
			sb.append("sequence="+ record.getSequence());
			sb.append(" path=" + record.getPath().canonicalValue());
		return sb;
	}
	
	private static final StringBuilder t(int depth) {
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
