package de.dbo.samples.util0;

import static de.dbo.samples.util0.Print.line;
import static de.dbo.samples.util0.Print.lines;
import static de.dbo.samples.util0.Print.linesNumbered;
import static de.dbo.samples.util0.Print.linesSorted;

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
import java.util.HashSet;
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
    public void test_000_null() {
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
        assertTrue("Printing null-properties lines returns not " + NULL + "-object"
        		 , ret == NULL);
        
        ret = line((Properties) null);
        log.debug("Null properties line: " + ret);
        assertTrue("Printing null-propertie line returns not " + NULL + "-object"
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
        
        ret = line((String[]) null);
        log.debug("Null string line: " + ret);
        assertTrue("Printing null-string objects as a line returns not " + NULL + "-object"
                , ret == NULL);
    }

    @Test
    public void test_010_empty() {
        final StringBuilder EMPTY = Print.EMPTY;
        StringBuilder ret = null;

        ret = lines(new HashMap<String, Printable>());
        log.debug("Empty map lines: " + ret);
        assertTrue("Printing empty-map lines returns not " + EMPTY + "-object"
                , ret.equals(EMPTY));
        
        ret = line(new HashMap<String, Printable>());
        log.debug("Empty map line: " + ret);
        assertTrue("Printing empty-map line returns not " + EMPTY + "-object"
                , ret.equals(EMPTY));
        
        ret = lines(new HashSet<String>());
        log.debug("Empty set lines: " + ret);
        assertTrue("Printing empty set lines returns not " + EMPTY + "-object"
        		 , ret == EMPTY);
        
        ret = lines(new Properties());
        log.debug("Empty properties lines: " + ret);
        assertTrue("Printing empty-properties lines returns not " + EMPTY + "-object"
        		 , ret == EMPTY);
        
        ret = line(new Properties());
        log.debug("Empty properties line: " + ret);
        assertTrue("Printing null-properties line returns not " + EMPTY + "-object"
        		 , ret == EMPTY);

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
    public void test_020_map() {
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
    public void test_021_properties() {
    	final Properties properties = sampleProperties(200);
    	log.debug("Properties line: " + line(properties));
    	log.debug("Properties lines: " + lines(properties));
    }
    
    @Test
    public void test_030_integers() {
        log.debug("integers: " + line(sampleintegers()) );
        log.debug("Integers: " + line(sampleIntegers()) );
        log.debug("Integers list: " + line(asList(sampleIntegers())) );
        
//TODO        log.debug("integers: " + linesNumbered(sampleintegers()) );
//TODO       log.debug("Integers: " + linesNumbered(sampleIntegers()) );
        log.debug("Integers list: " + linesNumbered(asList(sampleIntegers())) );
    }
    
    @Test
    public void test_040_strings() {
        log.debug("Strings: " + line(sampleStrings()) );
        log.debug("Strings numbered: " + linesNumbered(sampleStrings()) );
        log.debug("Strings list: " + line(asList(sampleStrings())) );
        log.debug("Strings list numbered: " + linesNumbered(asList(sampleStrings())) );
    }
    
    // line(List<?>) 
    // linesSorted(List<?>)
    // linesNumbered(List<?>)
    @Test
    public void test_050_lists() {
    	log.debug("List line: " + line( sampleMapList()) );
        log.debug("List lines numbered: " + linesNumbered( sampleMapList()) );
        log.debug("List lines sorted: " + linesSorted( sampleMapList()) );
    }
    
    // lines(Set<?>) 
    // linesSorted(Set<?>)
    // linesNumberes(Set<?>)
    @Test
    public void test_060_sets() {
    	log.debug("Set lines: " + lines( sampleMapSet()) );
    	log.debug("Set lines numbered: " + linesNumbered( sampleMapSet()) );
    	log.debug("Set lines sorted: " + linesSorted( sampleMapSet()) );
    }
    
    @Test
    public void test_100_conversions() {
    	final Map<?,?> map = sampleMap(1000);
    	final Map<?,?>  mapOfPrintables = toMapOfPrintables(map);
    	for (final Object value: mapOfPrintables.values()) {
    		assertTrue("Value in the map of printables is not printable"
    			,value instanceof Printable);
    	}
    	final Object printableObject = new PrintableObject(map);
    	assertTrue("Printable object is not printable"
    			,printableObject instanceof Printable);
    }
 
    // SMAPLE GENERATORS
    
    private static Properties sampleProperties(final int i) {
    	final Properties properties = new Properties ();
       	properties.put("c", "c-" + i*10000);
    	properties.put("a", "a-" + i*100);
    	properties.put("b", "b-" + i*1000);
        return properties;
    }
    
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
    	ret.add(sampleMap(109));
    	ret.add(sampleMap(103));
    	ret.add(sampleMap(108));
    	return ret;
    }
    
    private static Set<?> sampleMapSet() {
    	final List<Map<String,String>> ret = new ArrayList<Map<String,String>>();
    	ret.add(sampleMap(11));
    	ret.add(sampleMap(22));
    	ret.add(sampleMap(33));
    	return new HashSet<Object>( ret );
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
