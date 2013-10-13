package de.dbo.samples.util0.junit;

import static de.dbo.samples.util0.Print.line;
import static de.dbo.samples.util0.Print.lines;
import static de.dbo.samples.util0.Print.linesNumbered;
import static de.dbo.samples.util0.PrintConversions.toMapOfPrintables;

import static org.junit.Assert.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

import de.dbo.samples.util0.Print;
import de.dbo.samples.util0.Printable;
import de.dbo.samples.util0.PrintableMap;
import de.dbo.samples.util0.PrintableObject;

import static java.util.Arrays.asList;

import java.util.ArrayList;
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
    	final Map<String,Printable> map = toMapOfPrintables(sampleMap(0));
    	map.put("Integer",   new PrintableObject(new Integer(012)));
    	map.put("Integers",  new PrintableObject(sampleIntegers()));
    	map.put("String",    new PrintableObject("bababa"));
    	map.put("Strings",   new PrintableObject(sampleStrings()));
    	map.put("Printable ", printableObject(777));
    	map.put("Printable map", new PrintableMap(sampleMap(1)));
    	map.put("Printable map of printables", new PrintableObject(sampleMapPrintable(11)));
    	map.put("Printable object", new PrintableObject(sampleMap(2)));
    	map.put("Printable object null", new PrintableObject());

        log.debug("Complete map: " + lines(map));
        log.debug("Filtered map: " + lines(map, "a" /* filter */));
        log.debug("Complete map single-line: " + line(map));
    }
    
    @Test
    public void test_30_integers() {
        log.debug("integers: " + line(sampleintegers()) );
        log.debug("Integers: " + line(sampleIntegers()) );
        log.debug("Integers list: " + line(asList(sampleIntegers())) );
        
//TODO        log.debug("integers: " + linesNumbered(sampleintegers()) );
//TODO       log.debug("Integers: " + linesNumbered(sampleIntegers()) );
        log.debug("Integers list: " + linesNumbered(asList(sampleIntegers())) );
    }
    
    @Test
    public void test_40_strings() {
        log.debug("Strings: " + line(sampleStrings()) );
        log.debug("Strings list: " + line(asList(sampleStrings())) );
        
        log.debug("Strings list numbered: " + linesNumbered(asList(sampleStrings())) );
    }
    
    // line(List<?>) 
    // linesNumbered(List<?>)
    @Test
    public void test_50_lists() {
    	log.debug("List line: " + line( sampleMapList()) );
        log.debug("List lines numbered: " + linesNumbered( sampleMapList()) );
    }
 
    // HELPERS
    
    private static Map<String,String> sampleMap(final int i) {
    	final Map<String,String> map = new HashMap<String,String>();
    	map.put("a"+i, "a-" + i*100);
    	map.put("b"+i, "b-" + i*1000);
    	map.put("c"+i, "c-" + i*10000);
        return map;
    }
    
    private static Map<String,Printable> sampleMapPrintable(final int i) {
        return toMapOfPrintables(sampleMap(i));
    }
    
    private static List<?> sampleMapList() {
    	final List<Map<String,String>> ret = new ArrayList<Map<String,String>>();
    	ret.add(sampleMap(11));
    	ret.add(sampleMap(22));
    	ret.add(sampleMap(33));
    	return ret;
    }
    
    private static Integer[] sampleIntegers() {
        return new Integer[]{new Integer(0), new Integer(1), new Integer(2)};
    }
    private static int[] sampleintegers() {
        return new int[]{0, 1, 2};
    }
    private static String[] sampleStrings() {
        return new String[]{"ba", "ba", "ba"};
    }
    private static Printable printableObject(final int x) {
        return new Printable() {
        	private final Map<String,Printable> map = toMapOfPrintables(sampleMap(x));
        	public final StringBuilder printline() {
        			return line(map);
        	}
        	public final StringBuilder printlines() {
        		return lines(map);
    	}
        };
    }

}
