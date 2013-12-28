package de.dbo.samples.util0;

import static de.dbo.samples.util0.Print.cpCardinality;
import static de.dbo.samples.util0.Print.line;
import static de.dbo.samples.util0.Print.lines;
import static de.dbo.samples.util0.Print.linesNumbered;
import static de.dbo.samples.util0.Print.linesSorted;
import static de.dbo.samples.util0.Print.padLeft;
import static de.dbo.samples.util0.Print.padRight;
import static de.dbo.samples.util0.PrintConversions.toMapOfPrintables;
import static de.dbo.samples.util0.PrintConversions.toColllectionOfPrintables;
import static de.dbo.samples.util0.PrintConversions.toPrintable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;

import static org.junit.Assert.assertTrue;

import static org.slf4j.LoggerFactory.getLogger;

import static java.util.Arrays.asList;

/**
 *
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
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
        assertTrue("Printing null collection numbered lines returns not " + NULL + "-object"
                , ret == NULL);
        
        ret = linesNumbered(nullStrings);
        log.debug("Null strings numbered lines: " + ret);
        assertTrue("Printing null strings numbered lines returns not " + NULL + "-object"
                , ret == NULL);
        
        ret = toPrintable(null).printline();
        log.debug("Null line from to printable: " + ret);
        assertTrue("Printing null-line from toPrintable returns not " + NULL + "-object"
                , ret == NULL);
        
        ret = toPrintable(null).printlines();
        log.debug("Null lines from to printable: " + ret);
        assertTrue("Printing null-lines from toPrintable returns not " + NULL + "-object"
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
    	final Map<String,Printable> map = toMapOfPrintables(sampleNonPrintableMap(0));
    	map.put("Integer",   new PrintableObject(new Integer(12)));
    	map.put("Integers",  new PrintableObject(sampleIntegers()));
    	map.put("String",    new PrintableObject("bababa"));
    	map.put("Strings",   new PrintableObject(sampleStrings()));
    	map.put("Printable ", printableObject(777));
    	map.put("Printable map", new PrintableMap(sampleNonPrintableMap(1)));
    	map.put("Printable map of printables", new PrintableObject(sampleMapOfPrintables(11)));
    	map.put("Printable object", new PrintableObject(sampleNonPrintableMap(2)));
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
    	log.debug("Properties line filter:" + line(properties,"a"));
    	log.debug("Properties lines filter:" + lines(properties,"a"));
    }
    
	@Test
	public void test_022_printable_map_object() {
		final PrintableMap printableMap = new PrintableMap(sampleNonPrintableMap(333));
		log.debug("PrintableMap line: " + printableMap.printline());
		log.debug("PrintableMap lines: " + printableMap.printlines());
		
		final PrintableObject printableObject = new PrintableObject(sampleMapOfPrintables(666));
		log.debug("PrintableObject line: " + printableObject.printline());
		log.debug("PrintableObject lines: " + printableObject.printlines());
		
		final PrintableObject printableObjectNull = new PrintableObject((Map<?,?>)null);
		log.debug("PrintableObjectNull line: " + printableObjectNull.printline());
		log.debug("PrintableObjectNull lines: " + printableObjectNull.printlines());
		
		final PrintableObject printableObjectString = new PrintableObject("abcde");
		log.debug("PrintableObjectString line: " + printableObjectString.printline());
		log.debug("PrintableObjectString lines: " + printableObjectString.printlines());
		
		final Printable printable = printableObject(999);
		final PrintableObject printableObjectPrintable = new PrintableObject(printable);
		log.debug("PrintableObjectPrintable line: " + printableObjectPrintable.printline());
		log.debug("PrintableObjectPrintable lines: " + printableObjectPrintable.printlines());
		
		final Printable printable2 = toPrintable("abcdef");
		log.debug("PrintableObjectPrintable2 line: " + printable2.printline());
		log.debug("PrintableObjectPrintable2 lines: " + printable2.printlines());
		
		final Printable printable3 = toPrintable(new String[]{"a","b","c","d","e","f"});
		log.debug("PrintableObjectPrintable3 line: " + printable3.printline());
		log.debug("PrintableObjectPrintable3 lines: " + printable3.printlines());
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
    
    // lines(Collection<?>) 
    // linesSorted(Collection<?>)
    // linesNumberes(Collection<?>)
    @Test
    public void test_050_collections() {
    	log.debug("Collection lines:" + lines( (Collection<?>) sampleCollectionOfNonPrintableMaps(1)) );
    	log.debug("Collection lines numbered:" + linesNumbered( (Collection<?>) sampleCollectionOfNonPrintableMaps(2)) );
    	log.debug("Collection lines sorted:" + linesSorted( (Collection<?>) sampleCollectionOfNonPrintableMaps(3)) );
    }
    
    @Test
    public void test_060_levels() {
    	final Map<?,?> map = sampleNonPrintableMap(1000);
    	log.debug("Level map 0: "+lines(map, "1",0));
    	log.debug("Level map 1: "+lines(map, "1",1));
    	log.debug("Level map 2: "+lines(map, "1",2));
    	
    	log.debug("Level 0 maps empty filer: "+lines(map, "%%%%%",0));
    	
    	final Collection<?> list = sampleCollectionOfNonPrintableMaps(2);
    	log.debug("Level list 0: " + lines(list, "1",0));
    	log.debug("Level list 1: " + lines(list, "1",1));
    	log.debug("Level list 2: " + lines(list, "1",2));
    	
    	log.debug("Level 0 list empty filer: "+lines(list, "%%%%%",0));
    }
    
    @Test
    public void test_070_inverseMap() {
    	final Map<Object,String> map0 = new HashMap<Object,String>();
    	map0.put(new Integer(333),"Integer 333");
    	map0.put(new StringBuilder("444"),"StringBuilder 444");
    	map0.put(new String("555"),"String 555");
    	map0.put(sampleCollectionOfNonPrintableMaps(1),"Collection of non-printable maps");
    	map0.put(toColllectionOfPrintables(sampleCollectionOfNonPrintableMaps(2)),"Collection of printables");
    	map0.put(new PrintableObject(),"Printable null-object");
    	map0.put(new PrintableObject(toPrintable(Calendar.getInstance())),"Calendar");
    	map0.put(new PrintableObject(123456),"Printabe 123456-object");
    	map0.put(new Date(),"Date");
    	map0.put(new int[]{6,6,6} ,"int 6 6 6");
    	map0.put(new Integer[]{new Integer(3),new Integer(3),new Integer(3)} ,"Integer 3 3 3");
    	map0.put(new Integer[]{7,7,7} ,"Integer? 7 7 7 ");
    	final int expectedSize= 12;
    	
    	final Map<String,Printable> map0Converted = toMapOfPrintables(map0);
    	
    	log.debug("Map0 with object-keys:" + lines(map0Converted));
    	assertTrue("Converted map0 has wrong size " + map0Converted.size()
    			+ ". Expected " + expectedSize
    			,expectedSize==map0Converted.size());
    }
    
    @Test
    public void test_090_conversions() {
    	final Map<?,?> map = sampleNonPrintableMap(1000);
    	final Map<String,Printable>  mapOfPrintables = toMapOfPrintables(map);
    	for (final Object value: mapOfPrintables.values()) {
    		assertTrue("Value in the map of printables is not printable"
    			,value instanceof Printable);
    	}
    	final Object printableObject = new PrintableObject(map);
    	assertTrue("Printable object is not printable"
    			,printableObject instanceof PrintableObject);
    	
    	final Collection<?> collection = sampleCollectionOfNonPrintableMaps(33);
    	final StringBuilder collectionLines =  lines(collection);
    	final Collection<Printable> collectionOfPrintables = toColllectionOfPrintables(collection);
    	final StringBuilder collectionOfPrintablesLines =  lines(collectionOfPrintables);
    	log.debug("Lines for collection:"  + collectionLines);
    	log.debug("Lines for collection of printables:"  + collectionOfPrintablesLines);
    	
    }
    
    @Test
    public void test_100_cardinality() {
    	final Map<?,?> map = sampleNonPrintableMap(1000);
    	final Map<?,?> map2 = sampleNonPrintableMap(2000);
    	final Map<?,?> map3 = sampleNonPrintableMap(3000);
    	
    	final Map<String,Object> mapWithMaps = new HashMap<String,Object>();
    	mapWithMaps.put("x",  map);
    	mapWithMaps.put("x2", map2);
    	mapWithMaps.put("x3", map3);
    	mapWithMaps.put("x4", "fafafaf");
    	mapWithMaps.put("x5", null);
    	mapWithMaps.put("x6",  new HashMap<String,Object>());
    	mapWithMaps.put("x7",  new ArrayList<Object>() );
    	mapWithMaps.put("x8",  sampleCollectionOfNonPrintableMaps(11) );
    	assertTrue(8==mapWithMaps.size());
    	
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
    public void test_200() {
    	final int lenght = 20;
    	final String padRight = padRight("Howto", lenght);
    	final String padLeft = padLeft("Howto", lenght);
		log.debug("padRight = " + padRight + "*");
		log.debug("padLeft  = " + padLeft + "*");
		assertTrue(padRight + "has incorrect length "+ padRight.length() + ". Expected: " + lenght
				,lenght==padRight.length());
		assertTrue(padLeft + "has incorrect length " + padLeft.length() + ". Expected: " + lenght
				,lenght==padLeft.length());
	}

    
    // SMAPLE GENERATORS
    
    private static Properties sampleProperties(final int i) {
    	final Properties properties = new Properties ();
       	properties.put("c", "c-" + i*10000);
    	properties.put("a", "a-" + i*100);
    	properties.put("b", "b-" + i*1000);
        return properties;
    }
    
    private static Map<?,?> sampleNonPrintableMap(final int i) {
    	final Map<String,String> map = new HashMap<String,String>();
    	map.put("a"+i, "a-" + i*100);
    	map.put("b"+i, "b-" + i*1000);
    	map.put("c"+i, "c-" + i*10000);
        return map;
    }
    
    private static Map<String,Printable> sampleMapOfPrintables(final int i) {
        return toMapOfPrintables(sampleNonPrintableMap(i));
    }
    
    private static Collection<?> sampleCollectionOfNonPrintableMaps(final int i) {
    	final List<Object> ret = new ArrayList<Object>();
    	ret.add(sampleNonPrintableMap(i+10));
    	ret.add(sampleNonPrintableMap(i+100));
    	ret.add(sampleNonPrintableMap(i+1000));
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
			private final Map<String, Printable> map = toMapOfPrintables(sampleNonPrintableMap(x));

			public final StringBuilder printline() {
				return line(map);
			}

			public final StringBuilder printlines() {
				return lines(map);
			}
		};
	}

}
