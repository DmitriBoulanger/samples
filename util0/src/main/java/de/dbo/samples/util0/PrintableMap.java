package de.dbo.samples.util0;

import static de.dbo.samples.util0.Print.line;
import static de.dbo.samples.util0.Print.lines;

import java.util.HashMap;
import java.util.Map;

public final class PrintableMap implements Printable {
	
	/**
	 * collects values from the given map into map with printable values
	 * @param map
	 * @return map with printable values
	 */
	public static Map<String, Printable> toMapOfPrintables(final Map<String, ?> map) {
		final Map<String, Printable> ret = new HashMap<String, Printable>();
		for (Map.Entry<String, ?> entry: map.entrySet()) {
			put(entry,ret);
		}
		return ret;
	}
	
	private final Map<String, Printable> map = new HashMap<String, Printable>();
	
	/**
	 * creates a new printable map from the given map
	 * @param map
	 */
	public PrintableMap(final Map<String, ?> map) {
		for (Map.Entry<String, ?> entry: map.entrySet()) {
			put(entry,this.map);
		}
	}

	@Override
	public final StringBuilder printlines() {
		return lines(map);
	}

	@Override
	public final StringBuilder printline() {
		return line(map);
	}
	
	private static void put(final Map.Entry<String, ?> entry, final Map<String, Printable> map) {
		final String key = entry.getKey();
		final Object o = entry.getValue();
		if ( o instanceof Printable) {
			map.put(key, (Printable) o);
		} else {
			map.put(key, new PrintableObject(o));
		}
	}
}
