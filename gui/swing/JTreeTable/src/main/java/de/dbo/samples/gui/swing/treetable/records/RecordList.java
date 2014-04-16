package de.dbo.samples.gui.swing.treetable.records;

import de.dbo.samples.gui.swing.treetable.records.api.Node;
import de.dbo.samples.gui.swing.treetable.records.api.Path;
import de.dbo.samples.gui.swing.treetable.records.api.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecordList implements Comparable<RecordList> {
	private static final Logger log = LoggerFactory.getLogger(RecordList.class);
	
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
			record.setSequence( (long) this.records.size() );
			this.records.add(record);
		}
		process(this);
	}
	
	public Node tree() {
		return node;
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
	
	@Override
	public int compareTo(RecordList another) {
		return node.compareTo(another.node);
	}
	
	public int size() {
		return records.size();
	}
	
	public String nodeName() {
		return node.treeName();
	}
	
	public boolean nodeExpandable() {
		return !childRecordGroups.isEmpty() || 1!=records.size();
	}
	
	private static final void process(RecordList recordList) {
		String group = null;
		Long seq = null;
		RecordList groupList = null;
		final int depth = recordList.depth;
		for (Record record:recordList.records) {
			final String name = record.name();
			final Path path = record.getPath();
			final boolean isData = record.isData(depth);
			final String group2 = path.pathElement(depth);
			final Long seq2 = record.getSequence();
			if (null==group2) {
				continue;
			}
			
			if (group2.equals(group) && null!=seq && seq2 == seq+1) {
				if (isData) {
					groupList.node.getChildren().add(new NodeImpl(name,record,null));
				} else {
					groupList.records.add(record);
				}
			} else {
				group = group2;
				seq = seq2;
				final Node node = new NodeImpl(group, null, null);
				groupList = new RecordList(depth+1, node );
				if (isData) {
					groupList.node.getChildren().add(new NodeImpl(name,record,null));
				} else {
					groupList.records.add(record);
				}
				recordList.childRecordGroups.add(groupList);
			}
		}
		final List<Node> childern = recordList.node.getChildren();
		for (RecordList childRecordList: recordList.childRecordGroups)  {
			childern.add(childRecordList.node);
		}
		
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
			sb.append(" Node=" + recordList.node.print() + " sort=" + recordList.node.getSequence());
			sb.append(" Expandable="+ recordList.nodeExpandable());
			sb.append(" Cnt=" + recordList.size() + ":");
			for (Record record: recordList.records) {
				final boolean isData = record.isData(depth);
				if (record.isData(depth)) {
					dataCnt.incr();
				}
				sb.append(print(record, depth, t, isData) );
			}
			
			sb.append( print(recordList.childRecordGroups, depth+1, dataCnt));
		}
		return sb;
	}
	
	private static final StringBuilder print(Record record, int depth, StringBuilder t, boolean isData) {
		
		final StringBuilder sb = new StringBuilder();
			sb.append(t);
			sb.append("sequence="+ record.getSequence());
			sb.append(" path=" + record.getPath().canonicalValue());
			sb.append(" data=" + isData);
			
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
