package de.dbo.util0;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
		final StringBuilder sb = new StringBuilder();
		for (final String key: sortedKeys(map)) {
			sb.append(NL + key + " = " + map.get(key));
		}
		return lines(map, 0);
	}
	
	public static StringBuilder lines(final Map<String,String> map, final int offset) {
		final StringBuilder sb = new StringBuilder();
		final String nl = nl(offset);
		for (final String key: sortedKeys(map)) {
			sb.append(nl + key + " = " + map.get(key));
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
	
	private static final List<String> sortedKeys(final Map<String,String> map) {
		final List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);
		return keys;
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
