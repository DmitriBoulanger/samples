package de.dbo.samples.util0;

import static de.dbo.samples.util0.Print.line;
import static de.dbo.samples.util0.Print.lines;
import static de.dbo.samples.util0.PrintConversions.toPrintable;

import java.util.HashMap;
import java.util.Map;

public final class PrintableMap extends HashMap<String, Printable> implements Printable {
	private static final long serialVersionUID = -2780960984071643688L;
	
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
