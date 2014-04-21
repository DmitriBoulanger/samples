package de.dbo.samples.gui.swing.treetable.api.records;

import static de.dbo.samples.gui.swing.treetable.api.records.Tools.printInternalData;

import de.dbo.samples.gui.swing.treetable.api.factory.Factory;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordTreeGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Generator of the record-tree.
 * It takes input record-list and transforms it into the record-tree
 * using paths available in the records.
 * 
 * Input record-list is considered as ordered list. Records are ordered
 * using their sequence attributes. The output record-tree preserves the
 * order from the input record-list.
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public final class RecordTreeGenerator implements Comparable<RecordTreeGenerator>  {
	
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
	
	final Factory factory;
	
	/**
	 * 
	 * @param records
	 * 			record-list to be converted into the record-tree
	 */
	public RecordTreeGenerator(final Factory factory, final List<Record> records) {
		this.factory = factory;
		this.depth = 0;
		this.node = factory.newRoot();
		
		if (null==records || 0==records.size()) {
			return;
		}
		
		// if a record have the sequence-attribute, then it takes no new value 
		for (final Record record:records) {
			record.setSequence( (long) this.records.size() );
			this.records.add(record);
		}
		
		Collections.sort(this.records); // sort records using the sequence-attribute
		
		process(this, 0L); // generate initial brunches
		sort(this.node);   // sort children in nodes to ensure the original record-order
		merge(this.node);  // if possible merge all siblings with the same tree-names 
	}
	
	/**
	 * internal (recursive) constructor.
	 * It is only used while building the tree
	 * @param depth
	 * @param node
	 */
	RecordTreeGenerator(final Factory factory, final int depth, final Node node) {
		this.factory = factory;
		this.depth = depth;
		this.node = node;
	}
	
	public final int compareTo(RecordTreeGenerator another) {
		return node.compareTo(another.node);
	}
	
	public Node tree() {
		clear(this);
		return node;
	}
	
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
		final Factory factory = recordList.factory;
		Long seq = new Long( recordListSeq );
		String group = null;
		RecordTreeGenerator groupList = null;
		
		final int depth = recordList.depth;
		for (final Record record:recordList.records) {
			final String name = record.treename();
			final boolean isData = record.isDataDepth(depth);
			final String groupNext = record.getPath().pathElement(depth);
			if (null==groupNext) {
				continue;
			}
			
			final Long seqNext = record.getSequence();
			if (groupNext.equals(group) && null!=seq && seqNext == seq + 1) {
				if (isData) {
					groupList.node.getChildren().add(factory.newNode(name, record));
				} else {
					groupList.records.add(record);
				}
			} else {
				group = groupNext;
				seq = seqNext;
				final Node node = factory.newNode(group, null);
				node.setSequence(seq);
				groupList = new RecordTreeGenerator(recordList.factory, depth + 1, node );
				if (isData) {
					groupList.node.getChildren().add(factory.newNode(name, record));
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
					&& child.getTreename().equals(childNext.getTreename())) {
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
	private static final void clear(final RecordTreeGenerator recordList) {
		recordList.childRecordGroups.clear();
		recordList.records.clear();
	}
	
	/**
	 * only for debugging/developing.
	 * This method should be called before clear (the latter removes all internal data)
	 * 
	 * @return pretty-print of the internal data-structures
	 */
	public final StringBuilder print() {
		final StringBuilder ret = new StringBuilder();
		final StringBuilder sb =  printInternalData(this.childRecordGroups
				,0 ,true /* only nodes */);
		ret.append(sb);
		return ret;
	}

}
