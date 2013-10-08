package de.dbo.samples.util0.junit;
 
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.samples.util0.Print;

import java.util.*;

/**

 *
 * @author Dmitri Boulanger, Hombach
 *
 */
public class PrintTest {
	private static final Logger log = LoggerFactory.getLogger(PrintTest.class);
	
	@Test
	public void test_null() {
		 final String NULL = Print.NULL.toString();
		 String ret = null;
		 ret = Print.lines((Map<String,String>) null ).toString();
		 log.debug("Null map lines: " + ret);
		 assertTrue("Printing null-map returns not " + NULL + "-string"
				 ,ret.equals(NULL));
		 
		 ret = Print.lines((Set<Object>) null ).toString();
		 log.debug("Null set lines: " + ret);
		 assertTrue("Printing null-set returns not " + NULL + "-string"
				 ,ret.equals(NULL));
		 
		 ret =  Print.lines((List<Object>) null ).toString();
		 log.debug("Null list lines: " + ret);
		 assertTrue("Printing null-list returns not " + NULL + "-string"
				 ,ret.equals(NULL));
		 
		 ret =  Print.lines((Properties) null ).toString();
		 log.debug("Null properties lines: " + ret);
		 assertTrue("Printing null-properties returns not " + NULL + "-string"
				 ,ret.equals(NULL));
		 
		 ret =  Print.line((Map<String,String>) null ).toString();
		 log.debug("Null map line: " + ret);
		 assertTrue("Printing null-map as a line returns not " + NULL + "-string"
				 ,ret.equals(NULL));
		
		 ret =  Print.line((int[])null).toString();
		 log.debug("Null integers line: " + ret);
		 assertTrue("Printing null-integers as a line returns not " + NULL + "-string"
				 ,ret.equals(NULL));
		 
		 ret =   Print.line((Integer[])null).toString();
		 log.debug("Null integers line: " + ret);
		 assertTrue("Printing null-integer objects as a line returns not " + NULL + "-string"
				 ,ret.equals(NULL));
	}
	
	@Test
	public void test_empty() {
		 final String EMPTY = Print.EMPTY.toString();
		 String ret = null;
		 
		 ret = Print.lines(new HashMap<String,String>() ).toString();
		 log.debug("Empty map lines: " + ret);
		 assertTrue("Printing empry-map returns not " + EMPTY + "-string"
				 ,ret.equals(EMPTY));
		 
		 ret =  Print.line(new int[]{} ).toString();
		 log.debug("Empty integers line: " + ret);
		 assertTrue("Printing empty integers as a line returns not " + EMPTY + "-string"
				 ,ret.equals(EMPTY));
		 
		 ret = Print.line( new Integer[]{} ).toString();
		 log.debug("Empty integer-objects line: " + ret);
		 assertTrue("Printing empty integer objects as a line returns not " + EMPTY + "-string"
				 ,ret.equals(EMPTY));
	}
	
	@Test
	public void test() {
		 final Map<String,String> map = new HashMap<String,String>();
		 map.put("c", "C");
		 map.put("b", "B");
		 map.put("a", "A");
		 
		 log.debug("Complete map: " + Print.lines(map, null));
		 log.debug("Complete map single-line: " + Print.line(map));
		 log.debug("Filtered map: " + Print.lines(map, "a"));
		 
		 log.debug("Complete map: " + Print.lines(map, null));
		 log.debug("Complete map single-line: " + Print.line(map));
		 log.debug("Filtered map: " + Print.lines(map, "a"));
		 
		 final int[] integers = new int[] {1,2,3};
		 log.debug("Integers: " + Print.line(integers));
		 final Integer[] integersObjects = new Integer[] { new Integer(2), new Integer(3) };
		 log.debug("Integer objects: " + Print.line(integersObjects));
	}

}
