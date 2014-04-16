package de.dbo.samples.gui.swing.treetable.records;

import de.dbo.samples.gui.swing.treetable.records.api.Path;
import de.dbo.samples.gui.swing.treetable.records.api.Record;
import de.dbo.samples.gui.swing.treetable.records.impl.PathImpl;
import de.dbo.samples.gui.swing.treetable.records.impl.RecordImpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import static org.junit.Assert.*;


public class RecordsTest {
	
	Path child = new PathImpl("/a/b/c/d");
	
	Path parent = new PathImpl("/a/b/c");
	Path parent2 = new PathImpl("/a/b/c/d/e");
	Path parent3 = new PathImpl("/a/");
	Path parent4 = new PathImpl("/a/b/c/d/x");
	Path parent5 = new PathImpl("/a/b/c/d/");
	Path smth = new PathImpl("vsvsvs/a/bCC/c/d");
	
	Path px = new PathImpl("/a/b/c/f/k/f");
	Path px2 = new PathImpl("/a/b/c/f/k");
	Path px3 = new PathImpl("/a/b/c/f/");
	Path px4 = new PathImpl("/a");
	
	@Before
	public void open() {
		System.out.println("parent:  " + parent.print());
		System.out.println("parent2: " + parent2.print());
		System.out.println("parent3: " + parent3.print());
		System.out.println("parent4: " + parent4.print());
		System.out.println("parent5: " + parent5.print());
		System.out.println("child:   " + child.print());
		System.out.println("smth:    " + smth.print());
	}
	
	@After
	public void close() {
		
	}
	
	@Test
	public void childParentSiblingAsserts() {
		assertTrue("child should be child of parent"
				,child.isChildOf(parent));
		assertTrue("parent should be parent of child"
				,parent.isParentOf(child));
		assertFalse("child should not be child of parent2"
				,child.isChildOf(parent2));
		assertFalse("child should not be child of smth"
				,child.isChildOf(smth));
		assertFalse("smth should not be child of child"
				,smth.isChildOf(child));
		assertFalse("smth should not be child of smth"
				,smth.isChildOf(smth));
		assertFalse("parent should not be child of parent"
				,parent.isChildOf(parent));
		assertTrue("parent2 should be sibling of parent4"
				,parent2.isSiblingOf(parent4));
		
		assertFalse("should not be sibling of"
				,child.isSiblingOf(parent));
		assertFalse("should not be sibling of"
				,child.isSiblingOf(parent2));
		assertFalse("should not be sibling of"
				,parent.isSiblingOf(parent));
		
		 
	}
	
	@Test 
	public void recordList() {
		final List<Record> records = new ArrayList<Record>();
		records.add( new RecordImpl(parent ));
		records.add( new RecordImpl(parent2 ));
		records.add( new RecordImpl(px ));
		records.add( new RecordImpl(px ));
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
		
		final RecordTreeGenerator recordList = new RecordTreeGenerator(records);
		System.out.println("Tree-Structure: "
				 + "\nTotal records: " + recordList.size()  
	             + recordList.print());
		
	 
		 
	}

}
