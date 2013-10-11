package de.dbo.samples.util0.junit;

import static de.dbo.samples.util0.Print.line;
import static de.dbo.samples.util0.Print.lines;
import static org.junit.Assert.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

import de.dbo.samples.util0.Print;
import de.dbo.samples.util0.Printable;
import de.dbo.samples.util0.PrintableMap;
import de.dbo.samples.util0.PrintableObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;

/**
 *
 * @author Dmitri Boulanger, Hombach
 *
 */
public class PrintTest {
    private static final Logger log = getLogger(PrintTest.class);

    @Test
    public void test_00_null() {
        final StringBuilder NULL = Print.NULL;
        StringBuilder ret = null;
        ret = lines((Map<String, Printable>) null);
        log.debug("Null map lines: " + ret);
        assertTrue("Printing null-map returns not " + NULL + "-object"
        		 , ret == NULL);

        ret = lines((Set<String>) null);
        log.debug("Null set lines: " + ret);
        assertTrue("Printing null-set returns not " + NULL + "-object"
        		 , ret == NULL);

        ret = lines((List<String>) null);
        log.debug("Null list lines: " + ret);
        assertTrue("Printing null-list returns not " + NULL + "-object"
        		 , ret == NULL);

        ret = lines((Properties) null);
        log.debug("Null properties lines: " + ret);
        assertTrue("Printing null-properties returns not " + NULL + "-object"
        		 , ret == NULL);

        ret = line((Map<String, Printable>) null);
        log.debug("Null map line: " + ret);
        assertTrue("Printing null-map as a line returns not " + NULL + "-object"
        		 , ret == NULL);

        ret = line((int[]) null);
        log.debug("Null integers line: " + ret);
        assertTrue("Printing null-integers as a line returns not " + NULL + "-object"
        		 , ret == NULL);

        ret = line((Integer[]) null);
        log.debug("Null integers line: " + ret);
        assertTrue("Printing null-integer objects as a line returns not " + NULL + "-object"
                , ret == NULL);
    }

    @Test
    public void test_10_empty() {
        final StringBuilder EMPTY = Print.EMPTY;
        StringBuilder ret = null;

        ret = lines(new HashMap<String, Printable>());
        log.debug("Empty map lines: " + ret);
        assertTrue("Printing empry-map returns not " + EMPTY + "-object"
                , ret.equals(EMPTY));

        ret = line(new int[]{});
        log.debug("Empty integers line: " + ret);
        assertTrue("Printing empty integers as a line returns not " + EMPTY + "-object"
                , ret==EMPTY);

        ret = line(new Integer[]{});
        log.debug("Empty integer-objects line: " + ret);
        assertTrue("Printing empty integer objects as a line returns not " + EMPTY + "-object"
        		 , ret==EMPTY);
    }

    @Test
    public void test_20_map() {
    	final Map<String,Printable> map =  PrintableMap.toMapOfPrintables(map(0));
    	map.put("printable map", new PrintableMap(map(1)));
    	map.put("printable object", new PrintableObject(map(2)));
    	map.put("printable object null", new PrintableObject(null));

        log.debug("Complete map: " + lines(map, null));
        log.debug("Complete map single-line: " + line(map));
        log.debug("Filtered map: " + lines(map, "a"));
    }
    
    @Test
    public void test_30_integers() {
        final int[] integers = new int[]{1, 2, 3};
        log.debug("Integers: " + line(integers));
        final Integer[] integersObjects = new Integer[]{new Integer(2), new Integer(3)};
        log.debug("Integer objects: " + line(integersObjects));
    }
    
    @Test
    public void test_40_printable() {
    	 final PrintableMap map = new PrintableMap(map(0));
         log.debug("Printable map-lines: " + map.printlines());
         log.debug("Printable map-line: " + map.printline());
    }
    
    // HELPERS
    
    private static Map<String,String> map(final int i) {
    	final Map<String,String> map = new HashMap<String,String>();
    	map.put("a"+i, "x" + i*100);
    	map.put("b"+i, "x" + i*1000);
    	map.put("c"+i, "x" + i*10000);
        return map;
    }

}