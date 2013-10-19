package de.dbo.samples.util0;

import static de.dbo.samples.util0.PrintConversions.toMapOfPrintables;
import static de.dbo.samples.util0.Print.line;
import static de.dbo.samples.util0.Print.lines;

import java.util.Map;

final class PrintableObject implements Printable {

	private final Printable printable;
	private final Map<String, Printable> mapOfPrintables;
	private final StringBuilder stringBuffer;

	public PrintableObject() {
		this.printable = null;
		this.mapOfPrintables = null;
		this.stringBuffer = null;
	}

	public PrintableObject(final String o) {
		this.printable = null;
		this.mapOfPrintables = null;
		this.stringBuffer = (o == null ? null : new StringBuilder(o));
	}

	public PrintableObject(final StringBuilder o) {
		this.printable = null;
		this.mapOfPrintables = null;
		this.stringBuffer = (o == null ? null : o);
	}

	public PrintableObject(final int o) {
		this.printable = null;
		this.mapOfPrintables = null;
		this.stringBuffer = new StringBuilder(o);
	}

	public PrintableObject(final Integer o) {
		this.printable = null;
		this.mapOfPrintables = null;
		this.stringBuffer = 
				(o == null ? null : new StringBuilder(Integer.toString( o.intValue() )));
	}
	
	public PrintableObject(final Integer[] o) {
		this.printable = null;
		this.mapOfPrintables = null;
		this.stringBuffer = o == null ? null : new StringBuilder( line((Integer[]) o) );
	}
	
	public PrintableObject(final int[] o) {
		this.printable = null;
		this.mapOfPrintables = null;
		this.stringBuffer = o == null ? null : new StringBuilder( line(o) );
	}
	
	public PrintableObject(final String[] o) {
		this.printable = null;
		this.mapOfPrintables = null;
		this.stringBuffer = o == null ? null : new StringBuilder( line((String[]) o) );
	}

	public PrintableObject(final Map<?, ?> o) {
		this.printable = null;
		this.mapOfPrintables = (o == null ? null : toMapOfPrintables(o));
		this.stringBuffer = null;
	}

	public PrintableObject(final Printable o) {
		this.printable = o == null ? null : o;
		this.mapOfPrintables = null;
		this.stringBuffer = null;
	}

	@Override
	public StringBuilder printline() {
		if (null != printable) {
			return printable.printline();
		} else if (null != mapOfPrintables) {
			return line(mapOfPrintables);
		} else if (null != stringBuffer) {
			return stringBuffer;
		} else {
			return Print.NULL;
		}
	}

	@Override
	public StringBuilder printlines() {
		if (null != printable) {
			return printable.printlines();
		} else if (null != mapOfPrintables) {
			return lines(mapOfPrintables);
		} else if (null != stringBuffer) {
			return stringBuffer;
		} else {
			return Print.NULL;
		}
	}
}
