package de.dbo.samples.gui.swing.treetable.records.impl.ref;

import static de.dbo.samples.gui.swing.treetable.records.api.Node.ROOT;
import static de.dbo.samples.gui.swing.treetable.records.impl.ref.Tools.printInternalData;

import de.dbo.samples.gui.swing.treetable.records.api.Node;
import de.dbo.samples.gui.swing.treetable.records.api.Record;
import de.dbo.samples.gui.swing.treetable.records.api.RecordTreeGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public final class RecordTreeGeneratorImpl implements Comparable<RecordTreeGeneratorImpl>, RecordTreeGenerator {
	
	/**
	 * root-node to appear in a tree.
	 * After processing the input records (in the constructor),
	 * it contains valid complete tree.
	 */
	private final Node node;
	
	/**
	 * representation of children nodes.
	 * This list is only used while building the tree
	 */
	private final List<RecordTreeGenerator> childRecordGroups = new ArrayList<RecordTreeGenerator>();

	private final int depth;
	
	/**
	 * sorted input record-list
	 */
	private final List<Record> records = new ArrayList<Record>();
	
	/**
	 * 
	 * @param records
	 * 			record-list to be converted into the record-tree
	 */
	public RecordTreeGeneratorImpl(final List<Record> records) {
		this.depth = 0;
		this.node = new NodeImpl(ROOT, null, null);
		
		for (final Record record:records) {
			record.setSequence( (long) this.records.size() );
			this.records.add(record);
		}
		Collections.sort(this.records); // sort records using the sequence-attribute
		
		process(this, 0L); // generate initial brunches
		sort(this.node);   // sort children in nodes to ensure the original record-order
		merge(this.node);  // merge siblings with the same tree-names if possible
	}
	
	/**
	 * internal (recursive) constructor.
	 * It is only used while building the tree
	 * @param depth
	 * @param node
	 */
	RecordTreeGeneratorImpl(final int depth, final Node node) {
		this.depth = depth;
		this.node = node;
	}
	
	@Override
	public final int compareTo(RecordTreeGeneratorImpl another) {
		return node.compareTo(another.node);
	}
	
	/* (non-Javadoc)
	 * @see de.dbo.samples.gui.swing.treetable.records.impl.ref.RecordTreeGenerator#tree()
	 */
	@Override
	public Node tree() {
		clear(this);
		return node;
	}
	
	/* (non-Javadoc)
	 * @see de.dbo.samples.gui.swing.treetable.records.impl.ref.RecordTreeGenerator#size()
	 */
	@Override
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
		RecordTreeGeneratorImpl groupList = null;
		
		final int depth = recordList.getDepth();
		for (final Record record:recordList.getRecords()) {
			final String name = record.treename();
			final boolean isData = record.isDataDepth(depth);
			final String groupNext = record.getPath().pathElement(depth);
			if (null==groupNext) {
				continue;
			}
			
			final Long seqNext = record.getSequence();
			if (groupNext.equals(group) && null!=seq && seqNext == seq + 1) {
				if (isData) {
					groupList.node.getChildren().add(new NodeImpl(name, record, null));
				} else {
					groupList.records.add(record);
				}
			} else {
				group = groupNext;
				seq = seqNext;
				final Node node = new NodeImpl(group, null, null);
				node.setSequence(seq);
				groupList = new RecordTreeGeneratorImpl(depth + 1, node );
				if (isData) {
					groupList.node.getChildren().add(new NodeImpl(name, record, null));
				} else {
					groupList.records.add(record);
				}
				recordList.getChildRecordGroups().add(groupList);
			}
		}
		
		final List<Node> childern = recordList.getNode().getChildren();
		for (RecordTreeGenerator childRecordTreeGenerator: recordList.getChildRecordGroups())  {
			childern.add(childRecordTreeGenerator.getNode());
		}
		
		if (!recordList.getChildRecordGroups().isEmpty() ) {
			for (RecordTreeGenerator recordList2:recordList.getChildRecordGroups()) {
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
		final List<Node> children = node.getChildren();
		if (children.isEmpty()) {
			return;
		}
		final Set<Node> toBeRemoved = new HashSet<Node>();
		Node child = null;
		for (final Node childNext:children) {
//			System.err.println("CURRENT: " + (null!=child? child.print(): "null")  // DEBUGGING!
//					+"          NEXT: " + childNext.print());
			if (null!=child && !toBeRemoved.contains(childNext)
					&& child.treename().equals(childNext.treename())) {
				child.getChildren().addAll(childNext.getChildren());
//				child.setObject("got merge from " + childNext.print()); // DEBUGGING!
				toBeRemoved.add(childNext);
			} else {
				child = childNext;
			}
		}
		for (Node childToBeRemoved:toBeRemoved) {
			children.remove(childToBeRemoved);
		}
		
		for (final Node child2:children) {
			merge(child2);
		}
	}
	
	/**
	 * cleans up all lists that have been used while building record-tree
	 * @param recordList
	 */
	private static final void clear(final RecordTreeGeneratorImpl recordList) {
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

	@Override
	public List<RecordTreeGenerator> getChildRecordGroups() {
		return childRecordGroups;
	}

	@Override
	public List<Record> getRecords() {
		return records;
	}

	@Override
	public Node getNode() {
		return node;
	}
	
	@Override
	public int getDepth() {
		return depth;
	}
	
	 
	
}
