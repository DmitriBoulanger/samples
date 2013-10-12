package de.dbo.samples.util0;

import static de.dbo.samples.util0.Print.line;
import static de.dbo.samples.util0.Print.lines;
import static de.dbo.samples.util0.PrintableObject.toPrintable;

import java.util.HashMap;
import java.util.Map;

public final class PrintableMap extends HashMap<String, Printable> implements Printable {
	private static final long serialVersionUID = -2780960984071643688L;

	/**
	 * collects values from the given map into map with printable values
	 * @param map
	 * @return map with printable values
	 */
	public static Map<String, Printable> toMapOfPrintables(final Map<String, ?> map) {
		final Map<String, Printable> ret = new HashMap<String, Printable>();
		for (Map.Entry<String, ?> entry: map.entrySet()) {
			final String key = entry.getKey();
			final Object o = entry.getValue();
			ret.put(key, toPrintable(o));
		}
		return ret;
	}
	
	/**
	 * creates a new printable map from the given map
	 * @param map
	 */
	public PrintableMap(final Map<String, ?> map) {
		for (Map.Entry<String, ?> entry: map.entrySet()) {
			final String key = entry.getKey();
			final Object o = entry.getValue();
			put(key, toPrintable(o));
		}
	}

	@Override
	public final StringBuilder printlines() {
		return lines(this);
	}

	@Override
	public final StringBuilder printline() {
		return line(this);
	}
}
