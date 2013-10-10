package de.dbo.samples.util0;

public final class PrintableObject implements Printable {
	
	private final Object o;
	
	public PrintableObject(final Object o) {
		this.o = o;
	}

	@Override
	public StringBuilder printlines() {
		if (null==o) {
			return Print.NULL;
		} else if (o instanceof PrintableObject) {
			return ((PrintableObject) o).printlines();
		} else if (o instanceof PrintableMap) {
			return ((PrintableMap) o).printlines();
		} else if (o instanceof Printable) {
			return ((Printable) o).printlines();
		} else {
			return new StringBuilder(o.toString());
		}
	}

	@Override
	public StringBuilder printline() {
		if (null==o) {
			return Print.NULL;
		} else if (o instanceof PrintableObject) {
			return ((PrintableObject) o).printline();
		} else if (o instanceof PrintableMap) {
			return ((PrintableMap) o).printline();
		} else if (o instanceof Printable) {
			return ((Printable) o).printline();
		} else {
			return new StringBuilder(o.toString());
		}
	}

}
