package de.dbo.samples.util0;

import static de.dbo.samples.util0.PrintConversions.toMapOfPrintables;
import static de.dbo.samples.util0.PrintConversions.toColllectionOfPrintables;
import static de.dbo.samples.util0.Print.line;
import static de.dbo.samples.util0.Print.lines;

import java.util.Collection;
import java.util.Map;

final class PrintableObject implements Printable {

	private final Printable printable;
	private final Map<String, Printable> mapOfPrintables;
	private final Collection<Printable> collectionOfPrintables;
	private final StringBuilder stringBuffer;

	public PrintableObject() {
		this.printable = null;
		this.mapOfPrintables = null;
		this.collectionOfPrintables = null;
		this.stringBuffer = null;
	}

	public PrintableObject(final String o) {
		this.printable = null;
		this.mapOfPrintables = null;
		this.collectionOfPrintables = null;
		this.stringBuffer = nn(o);
	}

	public PrintableObject(final StringBuilder o) {
		this.printable = null;
		this.mapOfPrintables = null;
		this.collectionOfPrintables = null;
		this.stringBuffer = nn(o);
	}

	public PrintableObject(final int o) {
		this.printable = null;
		this.mapOfPrintables = null;
		this.collectionOfPrintables = null;
		this.stringBuffer = nn(o);
	}

	public PrintableObject(final Integer o) {
		this.printable = null;
		this.mapOfPrintables = null;
		this.collectionOfPrintables = null;
		this.stringBuffer = nn(o);
	}
	
	public PrintableObject(final Integer[] o) {
		this.printable = null;
		this.mapOfPrintables = null;
		this.collectionOfPrintables = null;
		this.stringBuffer = nn(o);
	}
	
	public PrintableObject(final int[] o) {
		this.printable = null;
		this.mapOfPrintables = null;
		this.collectionOfPrintables = null;
		this.stringBuffer = nn(o);
	}
	
	public PrintableObject(final String[] o) {
		this.printable = null;
		this.mapOfPrintables = null;
		this.collectionOfPrintables = null;
		this.stringBuffer = nn(o);
	}

	public PrintableObject(final Map<?, ?> o) {
		this.printable = null;
		this.mapOfPrintables = nn(o);
		this.collectionOfPrintables = null;
		this.stringBuffer = null;
	}
	
	public PrintableObject(final Collection<?> o) {
		this.printable = null;
		this.mapOfPrintables = null;
		this.collectionOfPrintables = nn(o);
		this.stringBuffer = null;
	}

	public PrintableObject(final Printable o) {
		this.printable = nn(o);
		this.mapOfPrintables = null;
		this.collectionOfPrintables = null;
		this.stringBuffer = null;
	}
	
	// HELPERS
	
	private static final StringBuilder nn(final String value) {
		return null==value? null 
				: new StringBuilder(value);
	}
	private static final StringBuilder nn(final StringBuilder value) {
		return null==value? null 
				: value;
	}
	private static final StringBuilder nn(final Integer value) {
		return value == null ? null 
				: new StringBuilder(Integer.toString( value.intValue() ));
	}
	private static final StringBuilder nn(final int value) {
		return new StringBuilder(value);
	}
	private static final StringBuilder nn(final int[] value) {
		return value == null ? null 
				: new StringBuilder( line(value) );
	}
	private static final StringBuilder nn(final Integer[] value) {
		return value == null ? null 
				: new StringBuilder( line(value) );
	}
	private static final StringBuilder nn(final String[] value) {
		return value == null ? null 
				: new StringBuilder( line(value) );
	}
	private static final Map<String,Printable> nn(final Map<?,?> value) {
		return value == null ? null 
				: toMapOfPrintables(value);
	}
	private static final  Collection<Printable> nn(final Collection<?> value) {
		return value == null ? null 
				: toColllectionOfPrintables(value);
	}
	private static final Printable nn(final Printable value) {
		return value == null ? null : value;
	}
	

	@Override
	public StringBuilder printline() {
		if (null != printable) {
			return printable.printline();
		} else if (null != mapOfPrintables) {
			return line(mapOfPrintables);
		} else if (null != collectionOfPrintables) {
			return line(collectionOfPrintables);
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
