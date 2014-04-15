package de.dbo.samples.gui.swing.treetable.records;

import de.dbo.samples.gui.swing.treetable.records.api.Node;
import de.dbo.samples.gui.swing.treetable.records.api.Path;
import de.dbo.samples.gui.swing.treetable.records.api.Record;

import java.util.ArrayList;
import java.util.List;

public class RecordList {
	
	private final List<RecordList> childRecordGroups = new ArrayList<RecordList>();

	/*
	 * node to appear in a tree
	 */
	private final Node node;
	
	private final int depth;
	private final List<Record> records = new ArrayList<Record>();
	
	public RecordList(List<Record> records) {
		this.depth = 0;
		this.node = new NodeImpl("ROOT",null,null);
		for (final Record record:records) {
			record.setSequence(this.records.size());
			this.records.add(record);
		}
		process();
	}
	
	/**
	 * internal (recursive) constructor 
	 * @param depth
	 * @param node
	 */
	RecordList(final int depth, final Node node) {
		this.depth = depth;
		this.node = node;
	}
	
	public int size() {
		return records.size();
	}
	
	public String nodeName() {
		return node.getName();
	}
	
	public boolean nodeExpandable() {
		return !childRecordGroups.isEmpty() || 1!=records.size();
	}
	
	private void attachChildren() {
		List<Node> childern = node.children();
		for (RecordList recordList: childRecordGroups)  {
			childern.add(recordList.node);
		}
	}
	
	private final void process() {
		process(this);
	}
	
	private static final void process(RecordList recordList) {
		String group = null;
		RecordList groupList = null;
		for (Record record:recordList.records) {
			final Path path = record.getPath();
			final String group2 = path.pathElement(recordList.depth);
			if (null==group2) {
				continue;
			}
			
			if (group2.equals(group)) {
				groupList.records.add(record);
			} else {
				group = group2;
				groupList = new RecordList(recordList.depth+1, new NodeImpl(group, null, null) );
				groupList.records.add(record);
				recordList.childRecordGroups.add(groupList);
			}
		}
		recordList.attachChildren();
		
		if (!recordList.childRecordGroups.isEmpty() ) {
			for (RecordList recordList2:recordList.childRecordGroups) {
				process(recordList2);
			}
		}
		
		
		
	}
	
	public StringBuilder print() {
		final StringBuilder ret = new StringBuilder();
		final Cnt dataCnt = new Cnt();
		StringBuilder sb =  print(this.childRecordGroups,0 ,dataCnt);
		ret.append("\nData counter: " + dataCnt.get());
		ret.append(sb);
		return ret;
		
	}
	
	private static StringBuilder print(List<RecordList> children, int depth, final Cnt dataCnt) {
		final StringBuilder sb = new StringBuilder();
		final StringBuilder t = t(depth);
		for (RecordList recordList: children) {
			sb.append(t);
			sb.append("#" + depth + "#");
			sb.append(" Node=" + recordList.node.print());
			sb.append(" Expandable="+ recordList.nodeExpandable());
			sb.append(" Cnt=" + recordList.size() + ":");
			for (Record record: recordList.records) {
				sb.append(print(record, depth, t, dataCnt) );
			}
			
			sb.append( print(recordList.childRecordGroups, depth+1, dataCnt));
		}
		return sb;
	}
	
	private static final StringBuilder print(Record record, int depth, StringBuilder t, Cnt dataCnt) {
		boolean isData = depth==record.getPath().depth();
		final StringBuilder sb = new StringBuilder();
			sb.append(t);
			sb.append("sequence="+ record.getSequence());
			sb.append(" path=" + record.getPath().canonicalValue());
			sb.append(" data=" + isData);
			if (isData) {
				dataCnt.incr();
			}
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
