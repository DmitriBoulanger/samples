package de.dbo.samples.gui.swing.treetable.records.impl;

import de.dbo.samples.gui.swing.treetable.records.api.Node;
import de.dbo.samples.gui.swing.treetable.records.api.Path;
import de.dbo.samples.gui.swing.treetable.records.api.Record;
import de.dbo.samples.gui.swing.treetable.records.impl.PathImpl;
import de.dbo.samples.gui.swing.treetable.records.impl.RecordImpl;
import de.dbo.samples.gui.swing.treetable.records.impl.RecordTreeGenerator;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;


public class RecordsTest {
	private static final Logger log = LoggerFactory.getLogger(RecordsTest.class);
	
	private final Path child = new PathImpl("/a/b/c/d");
	private final Path parent1 = new PathImpl("/a/b/c");
	private final Path parent2 = new PathImpl("/a/b/c/d/e");
	private final Path parent3 = new PathImpl("/a/");
	private final Path parent4 = new PathImpl("/a/b/c/d/x");
	private final Path parent5 = new PathImpl("/a/b/c/d/");
	private final Path smth = new PathImpl("vsvsvs/a/bCC/c/d");
	
	private final Path px1 = new PathImpl("/a/b/c/f/k/f");
	private final Path px2 = new PathImpl("/a/b/c/f/k");
	private final Path px3 = new PathImpl("/a/b/c/f/");
	private final Path px4 = new PathImpl("/a");
	
	@Before
	public void open() {
		log.info("parent1:  " + parent1.print());
		log.info("parent2: " + parent2.print());
		log.info("parent3: " + parent3.print());
		log.info("parent4: " + parent4.print());
		log.info("parent5: " + parent5.print());
		log.info("child:   " + child.print());
		log.info("smth:    " + smth.print());
	}
	
	@After
	public void close() {
		
	}
	
	@Test
	public void childParentSibling() {
		assertTrue("child should be child of parent1"
				,child.isChildOf(parent1));
		assertTrue("parent1 should be parent1 of child"
				,parent1.isParentOf(child));
		assertFalse("child should not be child of parent2"
				,child.isChildOf(parent2));
		assertFalse("child should not be child of smth"
				,child.isChildOf(smth));
		assertFalse("smth should not be child of child"
				,smth.isChildOf(child));
		assertFalse("smth should not be child of smth"
				,smth.isChildOf(smth));
		assertFalse("parent1 should not be child of parent1"
				,parent1.isChildOf(parent1));
		assertTrue("parent2 should be sibling of parent4"
				,parent2.isSiblingOf(parent4));
		
		assertFalse("should not be sibling of"
				,child.isSiblingOf(parent1));
		assertFalse("should not be sibling of"
				,child.isSiblingOf(parent2));
		assertFalse("should not be sibling of"
				,parent1.isSiblingOf(parent1));
		
		 
	}
	
	@Test 
	public void recordTreeGenerator() {
		final List<Record> records = new ArrayList<Record>();
		records.add( new RecordImpl(parent1 ));
		records.add( new RecordImpl(parent2 ));
		records.add( new RecordImpl(px1 ));
		records.add( new RecordImpl(px1 ));
		records.add( new RecordImpl(px2 ));
		records.add( new RecordImpl(px3 ));
		records.add( new RecordImpl(px4 ));
		records.add( new RecordImpl(smth ));
		records.add( new RecordImpl(parent3 ));
		records.add( new RecordImpl(parent4 ));
		records.add( new RecordImpl(smth ));
		records.add( new RecordImpl(smth ));
		records.add( new RecordImpl(parent5 ));
		records.add( new RecordImpl(child ));
		
		final RecordTreeGenerator recordTreeGenerator = new RecordTreeGenerator(records);
		log.info("Tree-Structure: "
				 + "\nTotal records: " + recordTreeGenerator.size()  
	             + recordTreeGenerator.print());
		
		final int recordsCnt = 14;
		assertTrue("Expected " + recordsCnt+ " records but found " + recordTreeGenerator.size()
				,recordsCnt==recordTreeGenerator.size());
		
		final Node treeroot = recordTreeGenerator.tree();
		
		
		assertTrue("Expected no records after clean but found " + recordTreeGenerator.size()
				,0==recordTreeGenerator.size());
		
	 
		 
	}

}
