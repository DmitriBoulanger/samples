package de.dbo.samples.util0.junit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.samples.util0.Print;
import de.dbo.samples.util0.Printable;
import de.dbo.samples.util0.PrintableMap;
import de.dbo.samples.util0.PrintableObject;
import static de.dbo.samples.util0.Print.line;
import static de.dbo.samples.util0.Print.lines;

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
        ret = lines((Map<String, Printable>) null).toString();
        log.debug("Null map lines: " + ret);
        assertTrue("Printing null-map returns not " + NULL + "-string"
                , ret.equals(NULL));

        ret = lines((Set<String>) null).toString();
        log.debug("Null set lines: " + ret);
        assertTrue("Printing null-set returns not " + NULL + "-string"
                , ret.equals(NULL));

        ret = lines((List<String>) null).toString();
        log.debug("Null list lines: " + ret);
        assertTrue("Printing null-list returns not " + NULL + "-string"
                , ret.equals(NULL));

        ret = lines((Properties) null).toString();
        log.debug("Null properties lines: " + ret);
        assertTrue("Printing null-properties returns not " + NULL + "-string"
                , ret.equals(NULL));

        ret = line((Map<String, Printable>) null).toString();
        log.debug("Null map line: " + ret);
        assertTrue("Printing null-map as a line returns not " + NULL + "-string"
                , ret.equals(NULL));

        ret = line((int[]) null).toString();
        log.debug("Null integers line: " + ret);
        assertTrue("Printing null-integers as a line returns not " + NULL + "-string"
                , ret.equals(NULL));

        ret = line((Integer[]) null).toString();
        log.debug("Null integers line: " + ret);
        assertTrue("Printing null-integer objects as a line returns not " + NULL + "-string"
                , ret.equals(NULL));
    }

    @Test
    public void test_empty() {
        final String EMPTY = Print.EMPTY.toString();
        String ret = null;

        ret = lines(new HashMap<String, Printable>()).toString();
        log.debug("Empty map lines: " + ret);
        assertTrue("Printing empry-map returns not " + EMPTY + "-string"
                , ret.equals(EMPTY));

        ret = line(new int[]{}).toString();
        log.debug("Empty integers line: " + ret);
        assertTrue("Printing empty integers as a line returns not " + EMPTY + "-string"
                , ret.equals(EMPTY));

        ret = line(new Integer[]{}).toString();
        log.debug("Empty integer-objects line: " + ret);
        assertTrue("Printing empty integer objects as a line returns not " + EMPTY + "-string"
                , ret.equals(EMPTY));
    }

    @Test
    public void test_map() {
    	final Map<String,Printable> map =  PrintableMap.map(map(0));
    	map.put("xx", new PrintableMap(map(1)));
    	map.put("yy", new PrintableObject(map(2)));

        log.debug("Complete map: " + lines(map, null));
        log.debug("Complete map single-line: " + line(map));
        log.debug("Filtered map: " + lines(map, "a"));

        log.debug("Complete map: " + lines(map, null));
        log.debug("Complete map single-line: " + line(map));
        log.debug("Filtered map: " + lines(map, "a"));

        final int[] integers = new int[]{1, 2, 3};
        log.debug("Integers: " + line(integers));
        final Integer[] integersObjects = new Integer[]{new Integer(2), new Integer(3)};
        log.debug("Integer objects: " + line(integersObjects));
    }
    
    @Test
    public void test_printable() {
    	 final PrintableMap map = new PrintableMap(map(0));
         log.debug("Printable map-lines: " + map.printlines());
         log.debug("Printable map-line: " + map.printline());
    }
    
    // HELPERS
    
   
    private static Map<String,String> map(final int i) {
    	final Map<String,String> map = new HashMap<String,String>();
    	map.put("a"+i, "" + i*100);
    	map.put("b"+i, "" + i*1000);
    	map.put("c"+i, "" + i*10000);
        return map;
    }

}
