package de.dbo.samples.util0;

import static de.dbo.samples.util0.Print.cpCardinality;
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
import java.util.Collection;
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
    	final Collection<?> nullCollection = null;
    	final String[] nullStrings = null;
        final StringBuilder NULL = Print.NULL;
        StringBuilder ret = null;
        
        ret = lines((Map<String, Printable>) null);
        log.debug("Null map lines: " + ret);
        assertTrue("Printing null-map returns not " + NULL + "-object"
        		 , ret == NULL);

        ret = lines(nullCollection);
        log.debug("Null set lines: " + ret);
        assertTrue("Printing null-collection returns not " + NULL + "-object"
        		 , ret == NULL);
        
        ret = line(nullCollection);
        log.debug("Null collection lines: " + ret);
        assertTrue("Printing null-list line returns not " + NULL + "-object"
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
        
        ret = linesSorted(nullCollection);
        log.debug("Null collection sorted lines: " + ret);
        assertTrue("Printing collection sorted returns not " + NULL + "-object"
                , ret == NULL);

        ret = line((int[]) null);
        log.debug("Null integers line: " + ret);
        assertTrue("Printing null-integers as a line returns not " + NULL + "-object"
        		 , ret == NULL);

        ret = line((Integer[]) null);
        log.debug("Null integers line: " + ret);
        assertTrue("Printing null-integer objects as a line returns not " + NULL + "-object"
                , ret == NULL);
        
        ret = line(nullStrings);
        log.debug("Null string line: " + ret);
        assertTrue("Printing null-string objects as a line returns not " + NULL + "-object"
                , ret == NULL);
        
        ret = lines(nullStrings);
        log.debug("Null string lines: " + ret);
        assertTrue("Printing null-string objects as a line returns not " + NULL + "-object"
                , ret == NULL);
        
        ret = line((String[])null);
        log.debug("Null string line: " + ret);
        assertTrue("Printing null-string objects as a line returns not " + NULL + "-object"
                , ret == NULL);
        
        ret = linesNumbered(nullCollection);
        log.debug("Null collection numbered lines: " + ret);
        assertTrue("Printing empty collection numbered lines returns not " + NULL + "-object"
                , ret == NULL);
        
        ret = linesNumbered(nullStrings);
        log.debug("Empty strings numbered lines: " + ret);
        assertTrue("Printing empty strings numbered lines returns not " + NULL + "-object"
                , ret == NULL);
    }

    @Test
    public void test_010_empty() {
    	final Collection<?> emptyCollection = new HashSet<Object>();
        final StringBuilder EMPTY = Print.EMPTY;
        StringBuilder ret = null;

        ret = lines(new HashMap<String, Printable>());
        log.debug("Empty map lines: " + ret);
        assertTrue("Printing empty-map lines returns not " + EMPTY + "-object"
        		 , ret == EMPTY);
        
        ret = line(new HashMap<String, Printable>());
        log.debug("Empty map line: " + ret);
        assertTrue("Printing empty-map line returns not " + EMPTY + "-object"
                , ret == EMPTY);
        
        ret = lines(emptyCollection);
        log.debug("Empty collection lines: " + ret);
        assertTrue("Printing empty collection lines returns not " + EMPTY + "-object"
        		 , ret == EMPTY);
        
        ret = line(emptyCollection);
        log.debug("Empty collection line: " + ret);
        assertTrue("Printing empty collection line returns not " + EMPTY + "-object"
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
        
        ret = line(new String[]{});
        log.debug("Empty strings line: " + ret);
        assertTrue("Printing empty strings as a line returns not " + EMPTY + "-object"
        		 , ret==EMPTY);
        
        ret = lines(new String[]{});
        log.debug("Empty strings line: " + ret);
        assertTrue("Printing empty strings as lines returns not " + EMPTY + "-object"
        		 , ret==EMPTY);
        
        ret = linesSorted(emptyCollection);
        log.debug("Empty collection sorted lines: " + ret);
        assertTrue("Printing empty collection sorted lines returns not " + EMPTY + "-object"
                , ret == EMPTY);
        
        ret = linesNumbered(emptyCollection);
        log.debug("Empty collection numbered lines: " + ret);
        assertTrue("Printing empty collection numbered lines returns not " + EMPTY + "-object"
                , ret == EMPTY);
        
        ret = linesNumbered(new String[]{});
        log.debug("Empty strings numbered lines: " + ret);
        assertTrue("Printing empty strings numbered lines returns not " + EMPTY + "-object"
                , ret == EMPTY);
    }

    @Test
    public void test_020_map() {
    	final Map<String,Printable> map = toMapOfPrintables(sampleMap(0));
    	map.put("Integer",   new PrintableObject(new Integer(12)));
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
    	log.debug("Properties line:" + line(properties));
    	log.debug("Properties lines:" + lines(properties));
    }
    
    @Test
    public void test_030_integers() {
        log.debug("integers:" + line(sampleintegers()) );
        log.debug("Integers:" + line(sampleIntegers()) );
        log.debug("Integers list line:" + line(asList(sampleIntegers())) );
        log.debug("Integers list numbered lines:" + linesNumbered(asList(sampleIntegers())) );
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
    	log.debug("List line:" + line( sampleMapList()) );
    	log.debug("List lines:" + lines( sampleMapList()) );
        log.debug("List lines numbered:" + linesNumbered( sampleMapList()) );
        log.debug("List lines sorted:" + linesSorted( sampleMapList()) );
    }
    
    // lines(Set<?>) 
    // linesSorted(Set<?>)
    // linesNumberes(Set<?>)
    @Test
    public void test_060_sets() {
    	log.debug("Set lines:" + lines( sampleMapSet()) );
    	log.debug("Set lines numbered:" + linesNumbered( sampleMapSet()) );
    	log.debug("Set lines sorted:" + linesSorted( sampleMapSet()) );
    }
    
    @Test
    public void test_090_conversions() {
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
    
    @Test
    public void test_100_misc() {
    	final Map<?,?> map = sampleMap(1000);
    	final Map<?,?> map2 = sampleMap(2000);
    	final Map<?,?> map3 = sampleMap(3000);
    	final Map<String,Object> mapWithMaps = new HashMap<String,Object>();
    	mapWithMaps.put("x",  map);
    	mapWithMaps.put("x2", map2);
    	mapWithMaps.put("x3", map3);
    	mapWithMaps.put("x4", "fafafaf");
    	mapWithMaps.put("x5", null);
    	mapWithMaps.put("x6",  new HashMap<String,Object>());
    	mapWithMaps.put("x7",  new ArrayList<Object>() );
    	mapWithMaps.put("x8",  sampleMapList() );
    	
    	final String cpCardinality = cpCardinality(mapWithMaps).toString();
    	final String cpCardinalityExpected = "CP-cardinaliy[3x0x0x1x0x3x3x3] = 000 000 081";
    	log.debug(cpCardinality);
    	assertTrue("cpCardinaly is not correct: " + cpCardinality
    			+ " Expected "+cpCardinalityExpected
    			,cpCardinality.equals(cpCardinalityExpected));
    	
    	
    	final String cpCardinality2 = cpCardinality(map).toString();
    	final String cpCardinalityExpected2 = "CP-cardinaliy[1x1x1] = 000 000 001";
    	log.debug(cpCardinality2);
    	assertTrue("cpCardinaly is not correct: " + cpCardinality2
    			+ " Expected "+cpCardinalityExpected2
    			,cpCardinality2.equals(cpCardinalityExpected2));
    }
    
    @Test
    public void test_101_misc() {
    	final Map<String,?> map = sampleMap(1000);
    	log.debug("Level map 0: "+lines(map, "1",0));
    	log.debug("Level map 1: "+lines(map, "1",1));
    	log.debug("Level map 2: "+lines(map, "1",2));
    	
    	log.debug("Level 0 maps empty filer: "+lines(map, "%%%%%",0));
    	
    	final List<?> list = sampleMapList();
    	log.debug("Level list 0: "+lines(list, "1",0));
    	log.debug("Level list 1: "+lines(list, "1",1));
    	log.debug("Level list 2: "+lines(list, "1",2));
    	
    	log.debug("Level 0 list empty filer: "+lines(list, "%%%%%",0));
    	
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
