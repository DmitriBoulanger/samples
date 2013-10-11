package de.dbo.samples.util0;

import java.util.Map;

public final class PrintableObject implements Printable {
	
	private final Printable o;
	private final Map<String,Printable> map;
	private final StringBuilder sb;
	
	@SuppressWarnings("unchecked")
	public PrintableObject(final Object o) {
		if (null==o) {
			this.o = null;
			this.map = null;
			this.sb = Print.NULL;
		} else if (o instanceof String) {
			this.o = null;
			this.map = null;
			this.sb = new StringBuilder( (String) o);
		} else if (o instanceof Integer) {
			this.o = null;
			this.map = null;
			this.sb = new StringBuilder( (Integer) o);
		} else if (o instanceof PrintableMap) {
			this.o = (PrintableMap) o;
			this.map = null;
			this.sb = null;
		} else if (isMapStringPrintable(o) ) {
			this.o = null;
			this.map =  (Map<String,Printable>) o;
			this.sb = null;
		} else if (o instanceof Printable) {
			this.o = (Printable) o;
			this.map = null;
			this.sb = null;
		} else {
			this.o = null;
			this.map = null;
			this.sb =  new StringBuilder(o.toString());
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
		if (null!=o) {
			return  o.printline()  ;
		} else if (null != map) {
			 return Print.line(map);
		} else {
			return sb;
		}
	}
	
	@Override
	public StringBuilder printlines() {
		if (null!=o) {
			return  o.printlines()  ;
		} else if (null != map) {
			 return Print.lines(map);
		} else {
			return sb;
		}
	}
}
