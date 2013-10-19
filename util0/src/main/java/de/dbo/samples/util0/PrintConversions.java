package de.dbo.samples.util0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static java.util.Collections.sort;

public final class PrintConversions {
    
    private PrintConversions() {
        // should be never initialized as an instance
    }

	/**
	 * collects keys and values from the given map 
	 * into a new map with string as keys and printable objects as values
	 * @param map
	 * @return map with string-keys and printable values
	 * @see #keyToString(Object)
	 * @see #toPrintable(Object)
	 */
	public static Map<String, Printable> toMapOfPrintables(final Map<?, ?> map) {
		final Map<String, Printable> ret = new HashMap<String, Printable>();
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			final Object keyObject = entry.getKey();
			final String key = keyToString(keyObject);
			final Object o = entry.getValue();
			final Printable oPrintable = toPrintable(o);
			ret.put(key, oPrintable);
		}
		return ret;
	}

	/**
	 * @param o object to be converted into a printable
	 * @return printable object
	 * @see PrintableObject
	 */
	public static final Printable toPrintable(final Object o) {
		if (null == o) {
			return new PrintableObject(Print.NULL);
		} else if (o instanceof Printable) {
			return (Printable) o;
		} else if (o instanceof String) {
			return new PrintableObject((String) o);
		} else if (o instanceof StringBuilder) {
			return new PrintableObject((StringBuilder) o);
		} else if (o instanceof Integer) {
			return new PrintableObject((Integer) o);
		} else if (o instanceof Map<?, ?>) {
			return new PrintableObject(toMapOfPrintables((Map<?, ?>) o));
		} else {
			return new PrintableObject(o.toString());
		}
	}

	protected static final String keyToString(final Object key) {
		final String keySring;
		if (key instanceof String)
			keySring = (String) key;
		else if (key instanceof Integer) {
			keySring = ((Integer) key).toString();
		} else if (key instanceof Printable) {
			keySring = ((Printable) key).printline().toString();
		} else {
			keySring = toPrintable(key).printline().toString();
		}
		return keySring;
	}

	static final List<String> sortedKeys(final Map<?, ?> map) {
		final List<String> keys = new ArrayList<String>();
		for (final Object o:map.keySet()){
			keys.add(keyToString(o));
		}
		sort(keys);
		return keys;
	}

	static final List<String> sortedKeys(final Properties properties) {
		final List<String> keys = new ArrayList<String>();
		keys.addAll(new ArrayList<String>(properties.stringPropertyNames()));
		sort(keys);
		return keys;
	}

	static final List<String> toSortedList(final Collection<?> collection) {
		final List<String> values = new ArrayList<String>();
		for (final Object o : collection) {
			values.add(keyToString(o));
		}
		sort(values);
		return values;
	}
}
