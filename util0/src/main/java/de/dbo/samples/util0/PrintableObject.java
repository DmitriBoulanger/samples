package de.dbo.samples.util0;

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
		this.sb = o == null ? null : new StringBuilder(o);
	}

	public PrintableObject(final Map<String, ?> o) {
		this.o = null;
		this.map = o == null ? null : PrintableMap.toMapOfPrintables(o);
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
			return Print.line(map);
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
			return Print.lines(map);
		} else if (null != sb) {
			return sb;
		} else {
			return Print.NULL;
		}
	}
}
