package de.dbo.samples.util0;

import static de.dbo.samples.util0.PrintableMap.toMapOfPrintables;
import static de.dbo.samples.util0.Print.line;
import static de.dbo.samples.util0.Print.lines;

import java.util.Map;

public final class PrintableObject implements Printable {

	public static final Printable toPrintable(final Object o) {
		if (null == o) {
			return new PrintableObject(Print.NULL);
		} else if (o instanceof String) {
			return new PrintableObject((String) o);
		} else if (o instanceof StringBuilder) {
			return new PrintableObject((StringBuilder) o);
		} else if (o instanceof Integer) {
			return new PrintableObject((Integer) o);
		} else if (o instanceof Printable) {
			return (Printable) o;
		} else if (o instanceof Map<?,?>) {
			return new PrintableObject( toMapOfPrintables( (Map<?,?>) o));
		} else {
			return new PrintableObject(o.toString());
		}
	}

	private final Printable o;
	private final Map<String, Printable> map;
	private final StringBuilder sb;;

	public PrintableObject() {
		this.o = null;
		this.map = null;
		this.sb = null;
	}

	public PrintableObject(final String o) {
		this.o = null;
		this.map = null;
		this.sb = o == null ? null : new StringBuilder(o);
	}

	public PrintableObject(final StringBuilder o) {
		this.o = null;
		this.map = null;
		this.sb = o == null ? null : o;
	}

	public PrintableObject(final int o) {
		this.o = null;
		this.map = null;
		this.sb = new StringBuilder(o);
	}

	public PrintableObject(final Integer o) {
		this.o = null;
		this.map = null;
		this.sb = o == null ? null : new StringBuilder(Integer.toString( o.intValue() ));
	}
	
	public PrintableObject(final Integer[] o) {
		this.o = null;
		this.map = null;
		this.sb = o == null ? null : new StringBuilder( line((Integer[]) o) );
	}
	
	public PrintableObject(final String[] o) {
		this.o = null;
		this.map = null;
		this.sb = o == null ? null : new StringBuilder( line((String[]) o) );
	}

	public PrintableObject(final Map<?, ?> o) {
		this.o = null;
		this.map = o == null ? null : toMapOfPrintables(o);
		this.sb = null;
	}

	public PrintableObject(final Printable o) {
		this.o = o == null ? null : o;
		this.map = null;
		this.sb = null;
	}

	@Override
	public StringBuilder printline() {
		if (null != o) {
			return o.printline();
		} else if (null != map) {
			return line(map);
		} else if (null != sb) {
			return sb;
		} else {
			return Print.NULL;
		}
	}

	@Override
	public StringBuilder printlines() {
		if (null != o) {
			return o.printlines();
		} else if (null != map) {
			return lines(map);
		} else if (null != sb) {
			return sb;
		} else {
			return Print.NULL;
		}
	}
}
