package de.dbo.samples.util0;

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
    public static final StringBuilder NULL  = new StringBuilder("NULL");
    public static final StringBuilder EMPTY = new StringBuilder("EMPTY");

    private static final String NL    = "\n\t - ";
    private static final String NL2   = "\n\t\t -- ";
    private static final String EQ = " = ";
    private static final String NB = ": ";

    private Print() {
        // should be never initialized as an instance
    }
    
   
    public static StringBuilder lines(final Map<String, Printable> map) {
        return lines(map, 0);
    }

    public static final StringBuilder lines(final Map<String, Printable> map, final String filter) {
        if (null == map) {
            return NULL;
        }
        if (0 == map.size()) {
            return EMPTY;
        }
        final StringBuilder sb = new StringBuilder();
        for (final String key : sortedKeys(map)) {
            if (null != filter && filter.trim().length() > 0) {
                if (-1 == key.indexOf(filter)) {
                    continue;
                }
            }
            sb.append(NL + key + EQ + new PrintableObject(map.get(key)).printline());
        }
        return sb;
    }

    public static StringBuilder lines(final Map<String, Printable> map, final int offset) {
        if (null == map) {
            return NULL;
        }
        if (0 == map.size()) {
            return EMPTY;
        }
        final StringBuilder sb = new StringBuilder();
        final String nl = nl(offset);
        for (final String key : sortedKeys(map)) {
            sb.append(nl + key + EQ + new PrintableObject(map.get(key)).printline());
        }
        return sb;
    }

    public static final StringBuilder lines(final Set<String> set) {
        if (null == set) {
            return NULL;
        }
        if (0 == set.size()) {
            return EMPTY;
        }
        final StringBuilder sb = new StringBuilder();
        for (final String o : toList(set)) {
            sb.append(NL + o);
        }
        return sb;
    }

    public static final StringBuilder lines(final List<String> list) {
        if (null == list) {
            return NULL;
        }
        if (0 == list.size()) {
            return EMPTY;
        }
        final StringBuilder sb = new StringBuilder();
        for (final String o : list) {
            sb.append(NL + o);
        }
        return sb;
    }

    public static final StringBuilder linesSorted(final Set<String> set) {
        if (null == set) {
            return NULL;
        }
        if (0 == set.size()) {
            return EMPTY;
        }
        final StringBuilder sb = new StringBuilder();
        for (final String key : sorted(set)) {
            sb.append(NL + key);
        }
        return sb;
    }

    public static final StringBuilder linesWithNumbers(final List<?> list) {
        if (null == list) {
            return NULL;
        }
        if (0 == list.size()) {
            return EMPTY;
        }
        final StringBuilder sb = new StringBuilder();
        int n = list.size();
        for (int i = 0; i < n; i++) {
            sb.append(NL + i + NB + list.get(i));
        }
        return sb;
    }

    public static final StringBuilder printWithNumbers(final String[] strings) {
        if (null == strings) {
            return NULL;
        }
        if (0 == strings.length) {
            return EMPTY;
        }
        final StringBuilder sb = new StringBuilder();
        int n = strings.length;
        for (int i = 0; i < n; i++) {
            sb.append(NL + i + ": " + strings[i]);
        }
        return sb;
    }

    public static StringBuilder line(final Map<String, Printable> map) {
        if (null == map) {
            return NULL;
        }
        if (0 == map.size()) {
            return EMPTY;
        }
        final StringBuilder sb = new StringBuilder();
        for (final String key : sortedKeys(map)) {
            sb.append(" [" + key + "=" + new PrintableObject(map.get(key)).printline() + "]");
        }
        return sb;
    }

    public static final StringBuilder line(final Integer[] integers) {
        if (null == integers) {
            return NULL;
        }
        if (0 == integers.length) {
            return EMPTY;
        }
        final StringBuilder sb = new StringBuilder();
        final int n = integers.length;
        for (int i = 0; i < n; i++) {
            sb.append(integers[i] + " ");
        }
        return sb;
    }

    public static final StringBuilder line(final List<?> integers) {
        if (null == integers) {
            return NULL;
        }
        final int n = integers.size();
        if (0 == n) {
            return EMPTY;
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(integers.get(i) + " ");
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
        final int n = integers.length;
        for (int i = 0; i < n; i++) {
            sb.append(integers[i] + " ");
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
            sb.append(NL + key + EQ + new PrintableObject(properties.getProperty(key)) );
        }
        return sb;
    }

    private static final List<String> sortedKeys(final Map<String, ?> map) {
        final List<String> keys = new ArrayList<String>();
        keys.addAll(new ArrayList<String>(map.keySet()));
        Collections.sort(keys);
        return keys;
    }

    private static final List<String> sortedKeys(final Properties p) {
        final List<String> keys = new ArrayList<String>();
        keys.addAll(new ArrayList<String>(p.stringPropertyNames()));
        Collections.sort(keys);
        return keys;
    }

    private static final List<String> sorted(final Set<String> set) {
        final List<String> values = new ArrayList<String>();
        values.addAll(new ArrayList<String>(set));
        Collections.sort(values);
        return values;
    }

    private static final List<String> toList(final Set<String> set) {
        final List<String> values = new ArrayList<String>();
        values.addAll(new ArrayList<String>(set));
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
