package de.dbo.samples.util.print;

import static de.dbo.samples.util.print.Print.line;
import static de.dbo.samples.util.print.Print.lines;
import static de.dbo.samples.util.print.PrintConversions.keyToString;
import static de.dbo.samples.util.print.PrintConversions.toPrintable;

import java.util.HashMap;
import java.util.Map;

/**
 * Internal (private) class to be used in the Print-algorithms
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
final class PrintableMap extends HashMap<String, Printable> implements Printable {
	private static final long serialVersionUID = -2780960984071643688L;
	
	/**
	 * creates a new printable map from the given map
	 * @param map
	 */
	PrintableMap(final Map<?, ?> map) {
		for (final Map.Entry<?, ?> entry: map.entrySet()) {
			final String key = keyToString(entry.getKey());
			final Printable value  = toPrintable(entry.getValue());
			put(key, value);
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
