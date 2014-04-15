package de.dbo.samples.gui.swing.treetable.records;

import org.junit.*;

import static org.junit.Assert.*;


public class PathTest {
	
	Path child = new Path("/a/b/c/d");
	
	Path parent = new Path("/a/b/c");
	Path parent2 = new Path("/a/b/c/d/e");
	Path parent3 = new Path("/a/");
	Path parent4 = new Path("/a/b/c/d/x");
	Path parent5 = new Path("/a/b/c/d/");
	Path smth = new Path("vsvsvs/a/bCC/c/d");
	
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
		
		parent5.addChild("y");
		System.out.println("parent5: " + parent5.print());
		assertTrue("parent4 should be sibling of parent5"
				,parent4.isSiblingOf(parent5));
	}
	
	@Test 
	public void recordList() {
		RecordList recordList = new RecordList();
		recordList.add( new Record(parent,System.currentTimeMillis() ));
		recordList.add( new Record(parent2,System.currentTimeMillis() ));
		recordList.add( new Record(smth,System.currentTimeMillis() ));
		recordList.add( new Record(parent3,System.currentTimeMillis() ));
		recordList.add( new Record(parent4,System.currentTimeMillis() ));
		recordList.add( new Record(smth,System.currentTimeMillis() ));
		recordList.add( new Record(parent5,System.currentTimeMillis() ));
		recordList.add( new Record(child,System.currentTimeMillis() ));
		recordList.process();
		
		System.out.println("Total records: " + recordList.size());
		System.out.println("Tree-structure:  " + recordList.print());
		 
	}

}
