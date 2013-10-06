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
		 log.info("Null map: " + Print.lines((Map<String,String>) null ));
		 log.info("Null integers: " + Print.line((int[])null));
		 log.info("Null integers: " + Print.line((Integer[])null));
	}
	
	@Test
	public void test_empty() {
		 log.info("Empty map: " + Print.lines(new HashMap<String,String>() ));
		 log.info("Empty integers: " + Print.line(new int[]{} ));
		 log.info("Empty integers: " + Print.line( new Integer[]{} ));
	}
	
	@Test
	public void test() {
		 final Map<String,String> map = new HashMap<String,String>();
		 map.put("c", "C");
		 map.put("b", "B");
		 map.put("a", "A");
		 log.info("Complete map: " + Print.lines(map, null));
		 log.info("Complete map single-line: " + Print.line(map));
		 log.info("Filtered map: " + Print.lines(map, "a"));
		 
		 log.info("Complete map: " + Print.lines(map, null));
		 log.info("Complete map single-line: " + Print.line(map));
		 log.info("Filtered map: " + Print.lines(map, "a"));
		 
		 final int[] integers = new int[] {1,2,3};
		 log.info("Integers: " + Print.line(integers));
		 final Integer[] integersObjects = new Integer[] { new Integer(2), new Integer(3) };
		 log.info("Integer objects: " + Print.line(integersObjects));
		 
	}

}
