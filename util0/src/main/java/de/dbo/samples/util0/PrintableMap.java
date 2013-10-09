package de.dbo.samples.util0;

import static de.dbo.samples.util0.Print.line;
import static de.dbo.samples.util0.Print.lines;

import java.util.HashMap;
import java.util.Map;

public final class PrintableMap implements Printable {
	
	private final Map<String, Printable> map = new HashMap<String, Printable>();
	
	public static Map<String, Printable> map(final Map<String, ?> map) {
		final Map<String, Printable> ret = new HashMap<String, Printable>();
		for (Map.Entry<String, ?> entry: map.entrySet()) {
			final String key = entry.getKey();
			ret.put(key, new PrintableObject(entry.getValue()));
		}
		return ret;
	}
	
	public PrintableMap(final Map<String, ?> map) {
		for (Map.Entry<String, ?> entry: map.entrySet()) {
			final String key = entry.getKey();
			this.map.put(key, new PrintableObject(entry.getValue()));
		}
	}
	
	public  Map<String, Printable> map() {
		return map;
	}

	@Override
	public final StringBuilder printlines() {
		return lines(map);
	}

	@Override
	public final StringBuilder printline() {
		return line(map);
	}

}
