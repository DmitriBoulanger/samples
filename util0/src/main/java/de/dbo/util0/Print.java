package de.dbo.util0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Pretty-print for debugging/logging
 * 
 * @author Dmitri Boulanger, Hombach
 *
 */
public final class Print {
	private static final String NL = "\n\t - ";
	private static final String NL2 = "\n\t\t -- ";
	
	private Print() {
		// should be never initialized as an instance
	}

	public static StringBuilder lines(final Map<String,String> map) {
		return lines(map, 0);
	}
	
	public static final String lines( final Map<String,?> map, String filter ) {
		final StringBuffer sb = new StringBuffer();
			for ( final String key:  sortedKeys(map)) {
				if ( null!=filter && filter.trim().length()>0) {
					if ( -1==key.indexOf(filter)   ){
						continue;
					}
				}
				sb.append(NL + key+"="+ map.get(key)  );
			}
		return sb.toString();
	}

	
	public static StringBuilder lines(final Map<String,String> map, final int offset) {
		final StringBuilder sb = new StringBuilder();
		final String nl = nl(offset);
		for (final String key: sortedKeys(map)) {
			sb.append(nl + key + " = " + map.get(key));
		}
		return sb;
	}
	
	public static final StringBuilder lines(final Set<Object> set){
		final StringBuilder sb = new StringBuilder();
		for (final Object o: toList(set)) {
			sb.append( NL + o );
		}
		return sb;
	}
	
	public static final StringBuilder lines(final List<Object> list){
		final StringBuilder sb = new StringBuilder();
		for (final Object o: list) {
			sb.append( NL + o );
		}
		return sb;
	}
	
	public static final StringBuilder linesSorted(final Set<String> set){
		final StringBuilder sb = new StringBuilder();
		for (final String key: sorted(set)) {
			sb.append(NL + key );
		}
		return sb;
	}

	
	public static final StringBuilder linesWithNumbers(final List<?> elements ) {
		final StringBuilder sb = new StringBuilder();
		int n = elements.size();
		for (int i=0; i<n; i++) {
			sb.append( NL + i+": " + elements.get(i) );
		}
		return sb;
	}
	
	public static final StringBuilder printWithNumbers (final String[] elements ) {
		final StringBuilder sb = new StringBuilder();
		int n = elements.length;
		for (int i=0; i<n; i++) {
			sb.append( NL +i+": " +  elements[i] );
		}
		return sb;
	}

	public static StringBuilder singleLine(final Map<String,String> map) {
		final StringBuilder sb = new StringBuilder();
		for (final String key: sortedKeys(map)) {
			sb.append(" [" + key + "=" + map.get(key) + "]");
		}
		return sb;
	}
	
	public static final StringBuilder singleLine(Integer[] elements) {
		final StringBuilder sb = new StringBuilder();
		final int n = elements.length;
		for (int i=0; i<n; i++) {
			sb.append( elements[i]+" " );
		}
		return sb;
	}
	
	public static final StringBuilder singleLine(int[] elements) {
		final StringBuilder sb = new StringBuilder();
		final int n = elements.length;
		for (int i=0; i<n; i++) {
			sb.append( elements[i]+" " );
		}
		return sb;
	}
	
	public static final StringBuilder lines(final Properties p) {
		final StringBuilder sb = new StringBuilder();
		for ( final String key: sortedKeys(p) ) {
			sb.append( NL +key+"="+p.getProperty(key));
		}
		return sb;
	}

	private static final List<String> sortedKeys(final Map<String,?> map) {
		final List<String> keys = new ArrayList<String>();
		keys.addAll( new ArrayList<String>(map.keySet()));
		Collections.sort(keys);
		return keys;
	}
	
	private static final List<String> sortedKeys(final Properties p) {
		final List<String> keys = new ArrayList<String>();
		keys.addAll( new ArrayList<String>(p.stringPropertyNames()));
		Collections.sort(keys);
		return keys;
	}
	
	private static final List<String> sorted(final Set<String> set) {
		final List<String> values =  new ArrayList<String>(); 
		values.addAll(new ArrayList<String>(set));
		Collections.sort(values);
		return values;
	}
	
	private static final List<?> toList(final Set<Object> set) {
		final List<Object> values = new ArrayList<Object>();
		values.addAll( new ArrayList<Object>(set));
		return values;
	}

	private static final String nl(final int offset) {
		switch (offset) {
		case 2:
			return NL2;
		default:
			return NL;
		}
	}
}
