package de.dbo.util0.junit;
 
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.util0.Print;

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
		 log.debug("Null map lines: " + Print.lines((Map<String,String>) null ));
		 log.debug("Null set lines: " + Print.lines((Set<Object>) null ));
		 log.debug("Null list lines: " + Print.lines((List<Object>) null ));
		 log.debug("Null properties lines: " + Print.lines((Properties) null ));
		 
		 log.debug("Null map line: " + Print.line((Map<String,String>) null ));
		
		 log.debug("Null integers line: " + Print.line((int[])null));
		 log.debug("Null integers line: " + Print.line((Integer[])null));
	}
	
	@Test
	public void test_empty() {
		 log.debug("Empty map lines: " + Print.lines(new HashMap<String,String>() ));
		 log.debug("Empty integers line: " + Print.line(new int[]{} ));
		 log.debug("Empty integer-objects line: " + Print.line( new Integer[]{} ));
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
