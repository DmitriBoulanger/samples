package de.dbo.samples.pico.simple.origin.juicer;

public class Juicer {
	private final Peelable peelable;
	private final Peeler peeler;

	public Juicer(Peelable peelable, Peeler peeler) {
		this.peelable = peelable;
		this.peeler = peeler;
	}
}
