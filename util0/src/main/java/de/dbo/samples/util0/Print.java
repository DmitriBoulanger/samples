package de.dbo.samples.util0;

import static de.dbo.samples.util0.PrintConversions.sortedKeys;
import static de.dbo.samples.util0.PrintConversions.toPrintable;
import static de.dbo.samples.util0.PrintConversions.toSortedList;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

import static java.util.Arrays.asList;

/**
 * Pretty-print for debugging/logging
 * 
 * @author Dmitri Boulanger, Hombach
 *
 */
public final class Print {
    public static final StringBuilder NULL  = new StringBuilder("NULL");
    public static final StringBuilder EMPTY = new StringBuilder("EMPTY");

    // internal constants
    private static final String NL    = "\n\t - ";
    private static final String NL2   = "\n\t\t -- ";
    private static final String EQ = " = ";
    private static final String NB = ": ";
    private static final String SP = " ";

    private Print() {
        // should be never initialized as an instance
    }
    
    public static StringBuilder lines(final Map<String, ?> map) {
    	return lines(map, null, 1);
    }
    
    public static final StringBuilder lines(final Map<String, ?> map, final String filter)  {
    	return lines(map, filter, 1);
    }
    
    public static StringBuilder line(final Map<String, ?> map) {
        return lines(map, null, 0);
    }

    public static final StringBuilder lines(final Map<String, ?> map
    		, final String filter, final int offset) {
        if (null == map) {
            return NULL;
        }
        if (0 == map.size()) {
            return EMPTY;
        }
        final String nl = nl(offset);
        final StringBuilder sb = new StringBuilder();
        for (final String key : sortedKeys(map)) {
            if (null != filter && filter.trim().length() > 0) {
                if (-1 == key.indexOf(filter)) {
                    continue;
                }
            }
            final StringBuilder value = toPrintable(map.get(key)).printline();
            sb.append(nl);
            sb.append(keyValue(key,value));
        }
        return sb;
    }
    
    public static final StringBuilder line(final Collection<?> collection) {
    	return lines(collection, null, 0);
    }
    
    public static final StringBuilder lines(final Collection<?> collection) {
    	return lines(collection, null, 1);
    }

    public static final StringBuilder lines(final Collection<?> collection
    		, final String filter, final int offset) {
        if (null == collection) {
            return NULL;
        }
        if (0 == collection.size()) {
            return EMPTY;
        }
        final String nl = nl(offset);
        final StringBuilder sb = new StringBuilder();
        for (final Object o : collection) {
        	final StringBuilder value = toPrintable(o).printline();
            if (null != filter && filter.trim().length() > 0) {
                if (-1 == value.indexOf(filter)) {
                    continue;
                }
            }
        	sb.append(nl);
            sb.append(toPrintable(o).printline());
        }
        return sb;
    }

    public static final StringBuilder linesSorted(final Collection<?> set) {
        if (null == set) {
            return NULL;
        }
        if (0 == set.size()) {
            return EMPTY;
        }
        final StringBuilder sb = new StringBuilder();
        for (final String key : toSortedList(set)) {
            sb.append(NL + key);
        }
        return sb;
    }

    public static final StringBuilder linesNumbered(final Collection<?> collection) {
        if (null == collection) {
            return NULL;
        }
        if (0 == collection.size()) {
            return EMPTY;
        }
        final StringBuilder sb = new StringBuilder();
        int i = 0;
        for (final Object o:collection) {
            sb.append(NL + number(i++) + NB + toPrintable(o).printline());
        }
        return sb;
    }

    public static final StringBuilder linesNumbered(final String[] strings) {
        if (null == strings) {
            return NULL;
        }
        if (0 == strings.length) {
            return EMPTY;
        }
        return linesNumbered(asList(strings));
    }


    public static final StringBuilder line(final Integer[] integers) {
        if (null == integers) {
            return NULL;
        }
        if (0 == integers.length) {
            return EMPTY;
        }
        final StringBuilder sb = new StringBuilder();
        for (final Integer i:integers) {
            sb.append( SP );
            sb.append(  i );
        }
        return sb;
    }
    
    public static final StringBuilder lines(final String[] strings) {
        if (null == strings) {
            return NULL;
        }
        if (0 == strings.length) {
            return EMPTY;
        }
        final StringBuilder sb = new StringBuilder();
        for (final String string:strings) {
        	 sb.append( NL );
        	 sb.append( string );
        }
        return sb;
    }
    
    public static final StringBuilder line(final String[] strings) {
        if (null == strings) {
            return NULL;
        }
        if (0 == strings.length) {
            return EMPTY;
        }
        final StringBuilder sb = new StringBuilder();
        for (final String string:strings) {
            sb.append( SP );
            sb.append(  string );
        }
        return sb;
    }

    public static final StringBuilder line(final int[] integers) {
        if (null == integers) {
            return NULL;
        }
        if (0 == integers.length) {
            return EMPTY;
        }
        final StringBuilder sb = new StringBuilder();
        for (final int i:integers) {
            sb.append( SP );
            sb.append(  i );
        }
        return sb;
    }

    public static final StringBuilder lines(final Properties properties) {
        if (null == properties) {
            return NULL;
        }
        if (0 == properties.size()) {
            return EMPTY;
        }
        final StringBuilder sb = new StringBuilder();
        for (final String key : sortedKeys(properties)) {
            sb.append(NL);
            sb.append(keyValue(key, properties.getProperty(key) ));
        }
        return sb;
    }
    
    public static final StringBuilder line(final Properties properties) {
        if (null == properties) {
            return NULL;
        }
        if (0 == properties.size()) {
            return EMPTY;
        }
        final StringBuilder sb = new StringBuilder();
        for (final String key : sortedKeys(properties)) {
        	sb.append(SP);
            sb.append(keyValue(key, properties.getProperty(key) ));
        }
        return sb;
    }
    
    private static final StringBuilder keyValue(final String key, final String value) {
    	return keyValue(key, new StringBuilder(value));
    }
    
    
    private static final StringBuilder keyValue(final String key, final StringBuilder value) {
    	final StringBuilder sb = new StringBuilder();
    	sb.append("[");
    	sb.append(key);
    	sb.append(EQ);
    	sb.append(value);
    	sb.append("]");
    	return sb;
    }
    

	private static final String nl(final int offset) {
		switch (offset) {
		case 0:
			return SP;
		case 1:
			return NL;
		case 2:
			return NL2;
		default:
			return NL;
		}
	}
	
	private static final String DF3 = "000";
	private static final String number(final int number) {
		return new DecimalFormat(DF3).format(number);
	}
}
