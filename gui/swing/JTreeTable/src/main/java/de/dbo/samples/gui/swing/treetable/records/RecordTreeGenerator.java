package de.dbo.samples.gui.swing.treetable.records;

import static de.dbo.samples.gui.swing.treetable.records.api.Node.ROOT;
import static de.dbo.samples.gui.swing.treetable.records.Tools.printInternalData;

import de.dbo.samples.gui.swing.treetable.records.api.Node;
import de.dbo.samples.gui.swing.treetable.records.api.Path;
import de.dbo.samples.gui.swing.treetable.records.api.Record;
import de.dbo.samples.gui.swing.treetable.records.impl.NodeImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class RecordTreeGenerator implements Comparable<RecordTreeGenerator> {
	
	final List<RecordTreeGenerator> childRecordGroups = new ArrayList<RecordTreeGenerator>();

	/*
	 * node to appear in a tree
	 */
	final Node node;
	
	final int depth;
	final List<Record> records = new ArrayList<Record>();
	
	/**
	 * Root of the tree-structure.
	 * 
	 * @param records all data-items of the the tree-structure
	 */
	public RecordTreeGenerator(final List<Record> records) {
		this.depth = 0;
		this.node = new NodeImpl(ROOT,null,null);
		for (final Record record:records) {
			record.setSequence( (long) this.records.size() );
			this.records.add(record);
		}
		Collections.sort(this.records);
		process(this, 0L);
		sort(this.node);
	}
	
	public Node tree() {
		clear(this);
		return node;
	}
	
	/**
	 * internal (recursive) constructor 
	 * @param depth
	 * @param node
	 */
	RecordTreeGenerator(final int depth, final Node node) {
		this.depth = depth;
		this.node = node;
	}
	
	private static final void clear(RecordTreeGenerator recordList) {
		recordList.childRecordGroups.clear();
		recordList.records.clear();
	}
	
	@Override
	public int compareTo(RecordTreeGenerator another) {
		return node.compareTo(another.node);
	}
	
	public int size() {
		return records.size();
	}
	
	private static final void sort(final Node node) {
		if (!node.getChildren().isEmpty() ) {
			Collections.sort(node.getChildren());
			for (Node child: node.getChildren()) {
				sort(child);
			}
		}
	}
	
	private static final void process(final RecordTreeGenerator recordList,  final Long recordListSeq) {
		Long seq = new Long( recordListSeq );
		String group = null;
		RecordTreeGenerator groupList = null;
		final int depth = recordList.depth;
		for (Record record:recordList.records) {
			final String name = record.treename();
			final Path path = record.getPath();
			final boolean isData = record.isDataDepth(depth);
			final String groupNext = path.pathElement(depth);
			if (null==groupNext) {
				continue;
			}
			final Long seqNext = record.getSequence();
			
			
			if (groupNext.equals(group) && null!=seq && seqNext == seq+1) {
				if (isData) {
					groupList.node.getChildren().add(new NodeImpl(name,record,null));
				} else {
					groupList.records.add(record);
				}
			} else {
				group = groupNext;
				seq = seqNext;
				final Node node = new NodeImpl(group, null, null);
				node.setSequence(seq);
				groupList = new RecordTreeGenerator(depth+1, node );
				if (isData) {
					groupList.node.getChildren().add(new NodeImpl(name,record,null));
				} else {
					groupList.records.add(record);
				}
				recordList.childRecordGroups.add(groupList);
			}
		}
		final List<Node> childern = recordList.node.getChildren();
		for (RecordTreeGenerator childRecordTreeGenerator: recordList.childRecordGroups)  {
			childern.add(childRecordTreeGenerator.node);
		}
		
		if (!recordList.childRecordGroups.isEmpty() ) {
			for (RecordTreeGenerator recordList2:recordList.childRecordGroups) {
				process(recordList2,seq+1);
			}
		}
	}
	
	/**
	 * only for debugging/developing.
	 * This method should be called before clear (the latter removes all internal data)
	 * 
	 * @return pretty-print of the internal data-structures
	 */
	StringBuilder print() {
		final StringBuilder ret = new StringBuilder();
		final StringBuilder sb =  printInternalData(this.childRecordGroups
				,0 ,true /* only nodes */);
		ret.append(sb);
		return ret;
		
	}
	
	

	
	
}
