package de.dbo.samples.jpa0.junit.impl;

import java.util.Properties;

public final class DerbyProperties extends Properties {
	private static final long serialVersionUID = -4571245668933236839L;

	public DerbyProperties() {
		put("derby.stream.error.file", "target/logs/derby.log");
	}

}
