package de.dbo.samples.util0;

import java.util.Map;

public final class PrintableObject implements Printable {
	
	private final Printable o;
	private final Map<String,Printable> map;
	private final StringBuilder string;
	
	@SuppressWarnings("unchecked")
	public PrintableObject(final Object o) {
		if (null==o) {
			this.o = null;
			this.map = null;
			this.string = Print.NULL;
		} else if (o instanceof String) {
			this.o = null;
			this.map = null;
			this.string = new StringBuilder( (String) o);
		} else if (o instanceof Integer) {
			this.o = null;
			this.map = null;
			this.string = new StringBuilder( (Integer) o);
		} else if (o instanceof PrintableMap) {
			this.o = (PrintableMap) o;
			this.map = null;
			this.string = null;
		} else if (isMapStringPrintable(o) ) {
			this.o = null;
			this.map =  (Map<String,Printable>) o;
			this.string = null;
		} else if (o instanceof Printable) {
			this.o = (Printable) o;
			this.map = null;
			this.string = null;
		} else {
			this.o = null;
			this.map = null;
			this.string =  new StringBuilder(o.toString());
		}
	}
	
	@SuppressWarnings("unchecked")
	private static final boolean isMapStringPrintable(Object o) {
		try {
			return null!=(Map<String,Printable>) o;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public StringBuilder printline() {
		return null != o?  o.printline()  
				: null != map? Print.line(map) 
				:  string;
	}
	
	@Override
	public StringBuilder printlines() {
		return null != o?  o.printlines() 
				: null != map? Print.lines(map) 
				:  string;
	}
}
