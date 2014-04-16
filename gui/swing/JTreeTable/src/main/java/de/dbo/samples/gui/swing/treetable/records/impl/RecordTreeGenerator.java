package de.dbo.samples.gui.swing.treetable.records.impl;

import static de.dbo.samples.gui.swing.treetable.records.api.Node.ROOT;
import static de.dbo.samples.gui.swing.treetable.records.impl.Tools.printInternalData;

import de.dbo.samples.gui.swing.treetable.records.api.Node;
import de.dbo.samples.gui.swing.treetable.records.api.Path;
import de.dbo.samples.gui.swing.treetable.records.api.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generator of the record-tree.
 * It takes input record-list and transforms this list into the record-tree
 * using the paths available in the records.
 * 
 * Input record-list is considered as ordered list. Records are ordered
 * using their sequence attributes. The output record-tree preserves the
 * order from the input record-list.
 * 
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class RecordTreeGenerator implements Comparable<RecordTreeGenerator> {
	
	/**
	 * root-node to appear in a tree.
	 * After processing the input records (in the constructor),
	 * it contains valid complete tree.
	 */
	final Node node;
	
	/**
	 * representation of children nodes.
	 * This list is only used while building the tree
	 */
	final List<RecordTreeGenerator> childRecordGroups = new ArrayList<RecordTreeGenerator>();

	final int depth;
	
	/**
	 * sorted input record-list
	 */
	final List<Record> records = new ArrayList<Record>();
	
	/**
	 * 
	 * @param records
	 * 			record-list to be converted into the record-tree
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
		merge(this.node);
	}
	
	/**
	 * internal (recursive) constructor.
	 * It is only used while building the tree
	 * @param depth
	 * @param node
	 */
	RecordTreeGenerator(final int depth, final Node node) {
		this.depth = depth;
		this.node = node;
	}
	
	@Override
	public int compareTo(RecordTreeGenerator another) {
		return node.compareTo(another.node);
	}
	
	/**
	 * complete record-tree.
	 * 
	 * @return root of the record-tree
	 */
	public Node tree() {
		clear(this);
		return node;
	}
	
	/**
	 * site of the input record-list
	 * @return
	 */
	public int size() {
		return records.size();
	}
	
	/**
	 * creates initial brunches of the record-tree.
	 * 
	 * @param recordList
	 * @param recordListSeq
	 */
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
	 * sorts brunches to have original record-order in the completely expanded record-tree
	 * @param node
	 */
	private static final void sort(final Node node) {
		if (!node.getChildren().isEmpty() ) {
			Collections.sort(node.getChildren());
			for (Node child: node.getChildren()) {
				sort(child);
			}
		}
	}
	
	/**
	 * tries to merge sibling-children with the same tree-name and the same depth
	 * @param node
	 */
	private static final void merge(final Node node) {
		if (!node.getChildren().isEmpty() ) {
			
			 //TODO
			
			for (Node child: node.getChildren()) {
				
				//TODO
				
				merge(child);
			}
		}
	}
	
	/**
	 * cleans up all lists that have been used while building record-tree
	 * @param recordList
	 */
	private static final void clear(RecordTreeGenerator recordList) {
		recordList.childRecordGroups.clear();
		recordList.records.clear();
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
